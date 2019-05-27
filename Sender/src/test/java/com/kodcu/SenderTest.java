package com.kodcu;

import com.kodcu.clustered.sender.verticle.ClusteredSender;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.kodcu.util.Constants.DEFAULT_HOSTNAME;
import static com.kodcu.util.Constants.DEFAULT_HTTP_PORT;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 9.10.2018
 */

@RunWith(VertxUnitRunner.class)
public class SenderTest
{
    private Vertx vertx;

    /**
     *
     * @param testContext
     */
    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(ClusteredSender.class.getName(), testContext.asyncAssertSuccess());
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
    public void sendingMessageTest(TestContext testContext) {

        final Async async = testContext.async();
        final WebClient client = WebClient.create(vertx);
        final String pathParam = "hello";

        client.post(DEFAULT_HTTP_PORT, DEFAULT_HOSTNAME, "/sendForAll/" + pathParam)
                .sendJsonObject(null, req -> {
                    if (req.succeeded()) {
                        testContext.assertTrue(req.result().bodyAsString().contains(pathParam));
                        async.complete();
                    }
                });
    }
}
