package com.kodcu.hw.verticle;

/*
 * Created by hakdogan on 10.07.2018
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class HelloWorldVerticle extends AbstractVerticle {

    /**
     *
     * @param future
     */
    @Override
    public void start(Future<Void> future) {
        vertx.createHttpServer().requestHandler(request -> request.response()
                .end("Hello from Vert.x application")).listen(config().getInteger("http.port", 8080), result -> {
                    if (result.succeeded()) {
                        future.complete();
                    } else {
                        future.fail(result.cause());
                    }
                });
    }


}
