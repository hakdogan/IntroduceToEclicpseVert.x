package com.kodcu.local.shared.maps.main;

import com.kodcu.local.shared.maps.verticle.SharerAndReaderLauncher;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2018
 */

public class Starter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Starter.class);

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new SharerAndReaderLauncher(), res -> {
            if(res.succeeded()){
                LOGGER.info("Deployment id is {}", res.result());
            } else {
                LOGGER.error("Deployment failed!");
            }
        });
    }
}
