package com.kodcu;

import com.kodcu.hw.verticle.HelloWorldVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
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
public class HelloWorldTest {

    private Vertx vertx;

    /**
     *
     * @param testContext
     */
    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(HelloWorldVerticle.class.getName(), testContext.asyncAssertSuccess());
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
        vertx.createHttpClient().getNow(DEFAULT_HTTP_PORT, DEFAULT_HOSTNAME, "/",
                response -> {
                    response.handler(responseBody -> {
                        testContext.assertTrue(responseBody.toString().contains("Hello from Vert.x application"));
                        async.complete();
                    });
                });
    }
}
