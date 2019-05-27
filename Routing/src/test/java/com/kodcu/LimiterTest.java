package com.kodcu;

import com.eviware.soapui.config.LoadTestLimitTypesConfig;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.loadtest.WsdlLoadTest;
import com.eviware.soapui.impl.wsdl.loadtest.WsdlLoadTestRunner;
import com.eviware.soapui.impl.wsdl.loadtest.data.LoadTestStatistics;
import com.eviware.soapui.impl.wsdl.loadtest.strategy.SimpleLoadStrategy;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.support.SoapUIException;
import com.kodcu.helper.ClusterConfiguratorHelper;
import com.kodcu.helper.RandomPortHelper;
import com.kodcu.routing.verticle.LimiterVerticle;
import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.XmlException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import static com.kodcu.helper.SoapUIHelper.*;
import static com.kodcu.util.Constants.SOAPUI_PROJECT_FILE;
import static org.junit.Assert.assertTrue;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2019-05-26
 */

@Slf4j
@RunWith(VertxUnitRunner.class)
public class LimiterTest
{
    private final String DEFAULT_HOSTNAME = "http://localhost:%s/limiting";
    private final String TEST_SUITE_NAME = "Limiter";
    private Vertx vertx;
    private int port;
    /**
     *
     * @param testContext
     */
    @Before
    public void setup(TestContext testContext) {
        port = RandomPortHelper.getRandomLocalPort();
        final Async async = testContext.async();
        final DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", port));
        final ClusterManager mgr = new HazelcastClusterManager(ClusterConfiguratorHelper.getHazelcastConfiguration());
        final VertxOptions clusterOptions = new VertxOptions().setClusterManager(mgr);

        Vertx.clusteredVertx(clusterOptions, cluster -> {
            if (cluster.succeeded()) {
                vertx = cluster.result();
                vertx.deployVerticle(LimiterVerticle.class.getName(), options, res -> {
                    if(res.succeeded()){
                        log.info("Deployment id is: {} ", res.result());
                        async.complete();
                    } else {
                        log.info("Deployment failed", res.cause());
                        testContext.fail(res.cause());
                    }
                });
            } else {
                log.error("Cluster up failed: {} ", cluster.cause());
            }
        });
    }

    /**
     *
     * @param testContext
     */
    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    public void tooManyRequestsTest(TestContext testContext) throws XmlException, IOException, SoapUIException {

        final Async async = testContext.async();
        WsdlProject project = new WsdlProject(SOAPUI_PROJECT_FILE);
        WsdlTestSuite testSuite = project.getTestSuiteByName("RateLimiter");
        WsdlTestCase testCase = testSuite.getTestCaseByName(TEST_SUITE_NAME);

        updateEndpoint(testCase.getTestStepList(), DEFAULT_HOSTNAME, port);
        WsdlLoadTest loadTest = testCase.getLoadTestAt(0);
        loadTest.setThreadCount(1);
        loadTest.setLimitType(LoadTestLimitTypesConfig.Enum.forString("COUNT"));
        loadTest.setTestLimit(40);

        loadTest.setLoadStrategy(new SimpleLoadStrategy(getLoadTestConfiguration(250, 0), loadTest));
        WsdlLoadTestRunner loadTestRunner = new WsdlLoadTestRunner(loadTest);
        while (50 <= getSecond()){ };
        loadTestRunner.start(true);
        loadTestRunner.waitUntilFinished();

        LoadTestStatistics statistics = loadTestRunner.getLoadTest().getStatisticsModel();
        long err = (long) statistics.getValueAt(1,10);

        assertTrue(err == 10);
        async.complete();

    }

}
