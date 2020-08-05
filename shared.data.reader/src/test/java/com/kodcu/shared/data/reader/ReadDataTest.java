package com.kodcu.shared.data.reader;

import com.kodcu.entity.StockExchange;
import com.kodcu.shared.data.reader.verticle.ReaderVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;
import org.junit.*;
import org.junit.runner.RunWith;

import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 19.10.2018
 */
@RunWith(VertxUnitRunner.class)
public class ReadDataTest
{

    private Vertx vertx;

    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(ReaderVerticle.class.getName(), testContext.asyncAssertSuccess());
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
    public void welcomePageTest(TestContext testContext){
        final Async async = testContext.async();
        final WebClient client = WebClient.create(vertx);

        client.get(DEFAULT_HTTP_PORT, DEFAULT_HOSTNAME, "/")
                .sendJsonObject(null, req -> {
                    if (req.succeeded()) {
                        testContext.assertTrue(req.result().bodyAsString().contains("Hello from Asynchronous Shared Data example!"));
                        async.complete();
                    }
                });
    }

    @Test
    public void readDataTest(TestContext testContext){
        final Async async = testContext.async();
        final SharedData sharedData = vertx.sharedData();
        vertx.setPeriodic(4000, asyncHandler -> {
            sharedData.<String, StockExchange>getAsyncMap(DEFAULT_ASYNC_MAP_NAME, res -> {
                if (res.succeeded()) {
                    AsyncMap<String, StockExchange> myAsyncMap = res.result();
                    myAsyncMap.get(DEFAULT_ASYNC_MAP_KEY, asyncDataResult -> {
                        StockExchange stockExchange = asyncDataResult.result();
                        testContext.assertNull(stockExchange);
                        async.complete();
                    });
                }
            });
        });
    }
}
