package com.kodcu.messenger.verticle;

/*
 * Created by hakdogan on 18.07.2018
 */

import com.kodcu.helper.VerticleDeployHelper;
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
        CompositeFuture.all(VerticleDeployHelper.deployHelper(vertx, ReceiverVerticle.class.getName()),
                VerticleDeployHelper.deployHelper(vertx, SenderVerticle.class.getName()))
                .setHandler(result -> {
                    if(result.succeeded()){
                        future.complete();
                    } else {
                        future.fail(result.cause());
                    }
                });
    }
}
