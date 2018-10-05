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

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 5.10.2018
 */

@RunWith(VertxUnitRunner.class)
public class HelloWorldTest {

    private Vertx vertx;
    private static final String HOSTNAME = "localhost";
    private static final int HTTP_PORT = 8080;

    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(HelloWorldVerticle.class.getName(), testContext.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    public void welcomePageTest(TestContext testContext) {
        final Async async = testContext.async();
        vertx.createHttpClient().getNow(HTTP_PORT, HOSTNAME, "/",
                response -> {
                    testContext.assertTrue(response.statusMessage().equals("OK"));
                    async.complete();
                });
    }
}
