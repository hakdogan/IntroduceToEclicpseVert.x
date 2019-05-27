package com.kodcu.main;

import com.kodcu.lds.verticle.LocalDataSharingAndReaderLauncher;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2018
 */

@Slf4j
public class Starter
{
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new LocalDataSharingAndReaderLauncher(), res -> {
            if(res.succeeded()){
                log.info("Deployment id is: {} ", res.result());
            } else {
                log.error("Deployment failed!");
            }
        });
    }
}
