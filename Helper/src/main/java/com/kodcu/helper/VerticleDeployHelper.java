package com.kodcu.helper;

import io.vertx.core.*;
import io.vertx.core.spi.cluster.ClusterManager;
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

    /**
     *
     * @param manager
     * @param className
     * @return
     */
    public static Future<Void> deployHelper(ClusterManager manager, String className){

        final Future<Void> future = Future.future();
        final ClusterManager mgr = manager;
        final VertxOptions options = new VertxOptions().setClusterManager(mgr);

        Vertx.clusteredVertx(options, cluster -> {
            if (cluster.succeeded()) {
                try {
                    cluster.result().deployVerticle((Verticle) Class.forName(className).newInstance(), res -> {
                        if(res.succeeded()){
                            log.info("Deployment id is: " + res.result());
                            future.complete();
                        } else {
                            log.error("Deployment failed!");
                            future.fail(res.cause());
                        }
                    });
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    log.error("Verticle deploy failed {} ", e);
                }
            } else {
                log.error("Cluster up failed: " + cluster.cause());
                future.fail(cluster.cause());
            }
        });

        return future;
    }
}
