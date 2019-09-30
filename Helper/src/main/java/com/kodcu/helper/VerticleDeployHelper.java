package com.kodcu.helper;

import io.vertx.core.*;
import io.vertx.core.spi.cluster.ClusterManager;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 9.10.2018
 */

@Slf4j
public class VerticleDeployHelper
{
    private VerticleDeployHelper() {}

    /**
     *
     * @param vertx
     * @param name
     * @return
     */
    public static Future<Void> deployHelper(final Vertx vertx, final String name){
        final Promise<Void> promise = Promise.promise();
        vertx.deployVerticle(name, res -> {
            if(res.failed()){
                log.error("Failed to deploy verticle!", name);
                promise.fail(res.cause());
            } else {
                log.info("{} verticle deployed!", name);
                promise.complete();
            }
        });

        return promise.future();
    }

    /**
     *
     * @param manager
     * @param className
     * @return
     */
    public static Promise<Void> deployHelper(final ClusterManager manager, final String className){

        final Promise<Void> promise = Promise.promise();
        final ClusterManager mgr = manager;
        final VertxOptions options = new VertxOptions().setClusterManager(mgr);

        Vertx.clusteredVertx(options, cluster -> {
            if (cluster.succeeded()) {
                try {
                    cluster.result().deployVerticle((Verticle) Class.forName(className).newInstance(), res -> {
                        if(res.succeeded()){
                            log.info("Deployment id is {}", res.result());
                            promise.complete();
                        } else {
                            log.error("Deployment failed!", res.cause());
                            promise.fail(res.cause());
                        }
                    });
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    log.error("Verticle deploy failed!", e);
                }
            } else {
                log.error("Cluster up failed!", cluster.cause());
                promise.fail(cluster.cause());
            }
        });

        return promise;
    }
}
