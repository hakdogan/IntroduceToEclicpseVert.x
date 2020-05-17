package com.kodcu.local.shared.maps.verticle;

import com.kodcu.helper.VerticleDeployHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Promise;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2018
 */

public class SharerAndReaderLauncher extends AbstractVerticle
{

    /**
     *
     * @param promise
     */
    @Override
    public void start(Promise<Void> promise){
        CompositeFuture.all(VerticleDeployHelper.deployHelper(vertx, SharerVerticle.class.getName()),
                VerticleDeployHelper.deployHelper(vertx, ReaderVerticle.class.getName()))
                .onComplete(result -> {
                    if(result.succeeded()){
                        promise.complete();
                    } else {
                        promise.fail(result.cause());
                    }
                });
    }
}
