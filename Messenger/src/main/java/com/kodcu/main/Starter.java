package com.kodcu.main;
/*
 * Created by hakdogan on 18.07.2018
 */

import com.kodcu.messenger.verticle.MessengerLauncher;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Starter
{

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MessengerLauncher(), res -> {
            if(res.succeeded()){
                log.info("Deployment id is: {} ", res.result());
            } else {
                log.error("Deployment failed!");
            }
        });
    }
}
