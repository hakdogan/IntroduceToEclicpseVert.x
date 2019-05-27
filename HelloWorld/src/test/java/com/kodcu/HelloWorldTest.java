package com.kodcu;

import com.kodcu.helper.RandomPortHelper;
import com.kodcu.hw.verticle.HelloWorldVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 5.10.2018
 */

@RunWith(VertxUnitRunner.class)
public class HelloWorldTest
{

    private Vertx vertx;
    private int port;

    /**
     *
     * @param testContext
     */
    @Before
    public void setup(TestContext testContext) throws IOException {
        vertx = Vertx.vertx();
        port = RandomPortHelper.getRandomLocalPort();
        DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", port));
        vertx.deployVerticle(HelloWorldVerticle.class.getName(), options, testContext.asyncAssertSuccess());
    }

    /**
     *
     * @param testContext
     */
    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    /**
     *
     * @param testContext
     */
    @Test
    public void welcomePageTest(TestContext testContext) {
        final Async async = testContext.async();
        final WebClient client = WebClient.create(vertx);
        client.get(port, DEFAULT_HOSTNAME, "/")
                .send(req -> {
                    if(req.succeeded()){
                        testContext.assertTrue(req.result().bodyAsString().contains("Hello from Vert.x application"));
                        async.complete();
                    } else {
                        testContext.fail(req.cause());
                    }
                });
    }
}
