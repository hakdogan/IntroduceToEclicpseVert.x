package com.kodcu.worker;

import com.kodcu.worker.verticle.WorkerVerticle;
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
 * Created on 6.10.2018
 */

@RunWith(VertxUnitRunner.class)
public class WriterTest
{
    private Vertx vertx;

    /**
     *
     * @param testContext
     */
    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(WorkerVerticle.class.getName(), testContext.asyncAssertSuccess());
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
    public void httpServerTest(TestContext testContext) {
        final Async async = testContext.async();
        vertx.createHttpClient().get(DEFAULT_HTTP_PORT, DEFAULT_HOSTNAME, "/",
                response -> {
                    response.result().handler(responseBody -> {
                        testContext.assertTrue(responseBody.toString().contains("Hello from Worker Verticle example!"));
                        async.complete();
                    });
                });
    }

    /**
     *
     * @param testContext
     */
    @Test
    public void writingTest(TestContext testContext){

        final Async async = testContext.async();
        final WebClient client = WebClient.create(vertx);
        final String word = "hello";

        client.post(DEFAULT_HTTP_PORT, DEFAULT_HOSTNAME, "/get/:" + word)
                .sendJsonObject(null, req -> {
                    if (req.succeeded()) {
                        testContext.assertTrue(req.result().bodyAsString().contains(word));
                        async.complete();
                    }
                });
    }

}
