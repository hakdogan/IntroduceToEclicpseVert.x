package com.kodcu.nonclustered.messenger.verticle;

/*
 * Created by hakdogan on 18.07.2018
 */

import com.kodcu.helper.VerticleDeployHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Promise;

public class MessengerLauncher extends AbstractVerticle
{

    /**
     *
     * @param promise
     */
    @Override
    public void start(Promise<Void> promise){
        CompositeFuture.all(VerticleDeployHelper.deployHelper(vertx, ReceiverVerticle.class.getName()),
                VerticleDeployHelper.deployHelper(vertx, SenderVerticle.class.getName()))
                .onComplete(result -> {
                    if(result.succeeded()){
                        promise.complete();
                    } else {
                        promise.fail(result.cause());
                    }
                });
    }
}
