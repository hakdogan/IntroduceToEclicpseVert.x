package com.kodcu;

import com.kodcu.entity.Data;
import com.kodcu.lds.verticle.LocalDataSharingAndReaderLauncher;
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

import static com.kodcu.util.Constants.DEFAULT_HOSTNAME;
import static com.kodcu.util.Constants.DEFAULT_HTTP_PORT;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2018
 */

@RunWith(VertxUnitRunner.class)
public class SharingAndReadingTest {

    private Vertx vertx;
    private Data data = new Data("key1", "value1");

    /**
     *
     * @param testContext
     */
    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(LocalDataSharingAndReaderLauncher.class.getName(), testContext.asyncAssertSuccess());
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
    public void putAndReadTest(TestContext testContext){
        final Async async = testContext.async();
        final WebClient client = WebClient.create(vertx);
        client.post(DEFAULT_HTTP_PORT, DEFAULT_HOSTNAME, "/put")
                .sendJsonObject(new JsonObject().mapFrom(data), req -> {
                    if (req.succeeded()) {
                        testContext.assertTrue(req.result().bodyAsString().contains(data.getKey()));
                        readTest(testContext);
                        async.complete();
                    }
                });
    }

    /**
     *
     * @param testContext
     */
    private void readTest(TestContext testContext){
        vertx.createHttpClient().getNow(8081, DEFAULT_HOSTNAME, "/read",
                response -> {
                    response.handler(responseBody -> {
                        testContext.assertTrue(responseBody.toString().contains(data.getValue()));
                    });
                });
    }

}
