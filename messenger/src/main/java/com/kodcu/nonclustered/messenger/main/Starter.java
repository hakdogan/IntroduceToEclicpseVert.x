package com.kodcu.nonclustered.messenger.main;
/*
 * Created by hakdogan on 18.07.2018
 */

import com.kodcu.nonclustered.messenger.verticle.MessengerLauncher;
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
        vertx.deployVerticle(new MessengerLauncher(), res -> {
            if(res.succeeded()){
                LOGGER.info("Deployment id is: " + res.result());
            } else {
                LOGGER.error("Deployment failed!");
            }
        });
    }
}
