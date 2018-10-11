package com.kodcu.lds.verticle;

import com.kodcu.helper.VerticleDeployHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2018
 */

public class LocalDataSharingAndReaderLauncher extends AbstractVerticle {

    /**
     *
     * @param future
     */
    @Override
    public void start(Future<Void> future){
        CompositeFuture.all(VerticleDeployHelper.deployHelper(vertx, SharingVerticle.class.getName()),
                VerticleDeployHelper.deployHelper(vertx, ReaderVerticle.class.getName()))
                .setHandler(result -> {
                    if(result.succeeded()){
                        future.complete();
                    } else {
                        future.fail(result.cause());
                    }
                });
    }
}
