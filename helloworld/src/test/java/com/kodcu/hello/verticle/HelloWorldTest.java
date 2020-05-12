package com.kodcu.hello.verticle;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;

import static com.kodcu.util.Constants.*;


/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 12.05.2020
 **/

@RunWith(VertxUnitRunner.class)
public class HelloWorldTest
{

    private final static Logger LOGGER = LoggerFactory.getLogger(HelloWorldTest.class);
    private Vertx vertx;
    private int port;

    /**
     *
     * @param testContext
     */
    @Before
    public void setup(TestContext testContext) throws IOException {
        vertx = Vertx.vertx();
        port = HelloWorldTest.getRandomLocalPort();
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

    public static int getRandomLocalPort(){
        try (ServerSocket socket = SSLServerSocketFactory.getDefault().createServerSocket(0); ){
            return socket.getLocalPort();
        } catch (Exception ex) {
            LOGGER.error("An exception was thrown in getRandomLocalPort method! " + ex);
        }
        return 0;
    }
}