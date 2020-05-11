package com.kodcu.hello.main;

/*
 * Created by hakdogan on 10.07.2018
 */

import com.kodcu.hello.verticle.HelloWorldVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class Starter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Starter.class);
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloWorldVerticle(), res -> {
            if (res.succeeded()) {
                LOGGER.info("Deployment id is " + res.result());
            } else {
                LOGGER.info("Deploymet failed! " + res.cause());
            }
        });
    }
}
