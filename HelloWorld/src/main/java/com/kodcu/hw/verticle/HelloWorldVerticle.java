package com.kodcu.hw.verticle;

/*
 * Created by hakdogan on 10.07.2018
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldVerticle extends AbstractVerticle
{

    /**
     *
     * @param future
     */
    @Override
    public void start(final Future<Void> future) {
        vertx.createHttpServer().requestHandler(request -> request.response()
                .putHeader("content-type", "text/html; charset=utf-8")
                .end("Hello from Vert.x application")).listen(config().getInteger("http.port", 8080), result -> {
                    if (result.succeeded()) {
                        log.info("HTTP server running on port {} ", 8080);
                        future.complete();
                    } else {
                        log.error("Could not start a HTTP server", result.cause());
                        future.fail(result.cause());
                    }
                });
    }
}
