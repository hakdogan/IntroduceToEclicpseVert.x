package com.kodcu;

import com.kodcu.messenger.verticle.MessengerLauncher;
import com.kodcu.messenger.verticle.SenderVerticle;
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

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 5.10.2018
 */

@RunWith(VertxUnitRunner.class)
public class MessageSendingTest {

    private Vertx vertx;
    private static final String HOSTNAME = "localhost";
    private static final int HTTP_PORT = 8080;

    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(MessengerLauncher.class.getName(), testContext.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    public void testSending(TestContext testContext) {

        final String pathParam = "message";
        final Async async = testContext.async();
        final WebClient client = WebClient.create(vertx);

        client.post(HTTP_PORT, HOSTNAME, "/send/" + pathParam)
                .sendJsonObject(new JsonObject()
                .put("title", "test title")
                .put("content", "test content")
                .put("author", "test author"), req -> {
            if (req.succeeded()) {
                testContext.assertTrue(req.result().bodyAsString().contains(pathParam));
                async.complete();
            }
        });
     }

}
