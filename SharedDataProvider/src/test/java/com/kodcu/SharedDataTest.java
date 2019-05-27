package com.kodcu;

import com.kodcu.asm.verticle.PutVerticle;
import com.kodcu.entity.StockExchange;
import io.vertx.core.Vertx;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.kodcu.util.Constants.DEFAULT_ASYNC_MAP_KEY;
import static com.kodcu.util.Constants.DEFAULT_ASYNC_MAP_NAME;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 17.10.2018
 */

@RunWith(VertxUnitRunner.class)
public class SharedDataTest
{

    private Vertx vertx;
    private StockExchange stockExchange;
    private String time;

    /**
     *
     * @param testContext
     */
    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(PutVerticle.class.getName(), testContext.asyncAssertSuccess());
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
    public void putDataTest(TestContext testContext){
        final Async async = testContext.async();
        vertx.setPeriodic(5000, handler -> {
            updateStockExchange();
            time = stockExchange.getTime();
            vertx.setPeriodic(2000, asyncHandler -> {
                updateStockExchange();
                testContext.assertFalse(time.equals(stockExchange.getTime()));
                async.complete();
            });
        });
    }

    private void updateStockExchange(){
        final SharedData sharedData = vertx.sharedData();
        sharedData.<String, StockExchange>getAsyncMap(DEFAULT_ASYNC_MAP_NAME, res -> {
            if (res.succeeded()) {
                AsyncMap<String, StockExchange> myAsyncMap = res.result();
                myAsyncMap.get(DEFAULT_ASYNC_MAP_KEY, asyncDataResult -> {
                   stockExchange = asyncDataResult.result();
                });
            }
        });
    }
}
