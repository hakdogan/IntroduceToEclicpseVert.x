package com.kodcu.messenger.verticle;

/*
 * Created by hakdogan on 18.07.2018
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessengerLauncher extends AbstractVerticle {

    /**
     *
     * @param future
     */
    @Override
    public void start(Future<Void> future){

        CompositeFuture.all(deployHelper(ReceiverVerticle.class.getName()),
                deployHelper(SenderVerticle.class.getName()))
                .setHandler(result -> {
                    if(result.succeeded()){
                        future.complete();
                    } else {
                        future.fail(result.cause());
                    }
                });
    }

    /**
     *
     * @param name
     * @return
     */
    private Future<Void> deployHelper(String name){

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
