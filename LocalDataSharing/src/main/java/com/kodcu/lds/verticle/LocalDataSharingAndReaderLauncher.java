package com.kodcu.lds.verticle;

import com.kodcu.helper.VerticleDeployHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Promise;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2018
 */

public class LocalDataSharingAndReaderLauncher extends AbstractVerticle
{

    /**
     *
     * @param promise
     */
    @Override
    public void start(Promise<Void> promise){
        CompositeFuture.all(VerticleDeployHelper.deployHelper(vertx, SharingVerticle.class.getName()),
                VerticleDeployHelper.deployHelper(vertx, ReaderVerticle.class.getName()))
                .setHandler(result -> {
                    if(result.succeeded()){
                        promise.complete();
                    } else {
                        promise.fail(result.cause());
                    }
                });
    }
}
