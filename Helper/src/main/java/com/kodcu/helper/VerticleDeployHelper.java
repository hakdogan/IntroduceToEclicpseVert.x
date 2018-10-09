package com.kodcu.helper;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 9.10.2018
 */

@Slf4j
public class VerticleDeployHelper {

    private VerticleDeployHelper() {}

    /**
     *
     * @param vertx
     * @param name
     * @return
     */
    public static Future<Void> deployHelper(Vertx vertx, String name){
        final Future<Void> future = Future.future();
        vertx.deployVerticle(name, res -> {
            if(res.failed()){
                log.error("Failed to deploy verticle " + name);
                future.fail(res.cause());
            } else {
                log.info("Deployed verticle " + name);
                future.complete();
            }
        });

        return future;
    }
}
