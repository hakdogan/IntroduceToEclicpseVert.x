package com.kodcu;

import com.kodcu.messenger.verticle.MessengerLauncher;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 5.10.2018
 */

@RunWith(VertxUnitRunner.class)
public class MessageSendingTest {

    private Vertx vertx;

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

        final Async async = testContext.async();
        final WebClient client = WebClient.create(vertx);
        final String pathParam = "hello";

        client.post(DEFAULT_HTTP_PORT, DEFAULT_HOSTNAME, "/send/" + pathParam)
                .sendJsonObject(null, req -> {
            if (req.succeeded()) {
                testContext.assertTrue(req.result().bodyAsString().contains(pathParam));
                async.complete();
            }
        });
     }

}
