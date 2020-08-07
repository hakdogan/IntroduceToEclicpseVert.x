package com.kodcu.local.shared.maps;

import com.kodcu.entity.SimpleData;
import com.kodcu.local.shared.maps.verticle.SharerAndReaderLauncher;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
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
public class SharingAndReadingTest
{

    private Vertx vertx;
    private SimpleData data = new SimpleData("key1", "value1");

    /**
     *
     * @param testContext
     */
    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(SharerAndReaderLauncher.class.getName(), testContext.asyncAssertSuccess());
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
                    } else {
                        testContext.fail(req.cause());
                    }
                });
    }

    /**
     *
     * @param testContext
     */
    private void readTest(TestContext testContext){
        final Async async = testContext.async();
        final WebClient client = WebClient.create(vertx);
        client.get(8081, DEFAULT_HOSTNAME, "/read")
                .send(req -> {
                    if(req.succeeded()){
                        testContext.assertTrue(req.result().bodyAsString().contains(data.getValue()));
                        async.complete();
                    } else {
                        testContext.fail(req.cause());
                    }
                });
    }

}
