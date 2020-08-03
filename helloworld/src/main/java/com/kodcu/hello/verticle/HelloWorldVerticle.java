package com.kodcu.hello.verticle;

/*
 * Created by hakdogan on 10.07.2018
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.kodcu.util.Constants.DEFAULT_HTTP_PORT;

public class HelloWorldVerticle extends AbstractVerticle
{
    private final Logger logger = LoggerFactory.getLogger(HelloWorldVerticle.class);
    /**
     *
     * @param promise
     */
    @Override
    public void start(final Promise<Void> promise) {
        vertx.createHttpServer().requestHandler(request -> request.response()
                .putHeader("content-type", "text/html; charset=utf-8")
                .end("Hello from Vert.x application")).listen(config().getInteger("http.port", DEFAULT_HTTP_PORT), result -> {
                    if (result.succeeded()) {
                        logger.info("HTTP server running on port {}", config().getInteger("http.port", DEFAULT_HTTP_PORT));
                        promise.complete();
                    } else {
                        logger.error("Could not start a HTTP server. ", result.cause());
                        promise.fail(result.cause());
                    }
                });
    }
}
