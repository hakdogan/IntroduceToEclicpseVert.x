package com.kodcu.main;

/*
 * Created by hakdogan on 10.07.2018
 */

import com.kodcu.hw.verticle.HelloWorldVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Starter {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloWorldVerticle(), res -> {
            if (res.succeeded()) {
                log.info("Deployment id is: " + res.result());
            } else {
                log.info("Deployment failed!");
            }
        });
    }
}
