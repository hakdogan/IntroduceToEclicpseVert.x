package com.kodcu.helper;

import io.vertx.core.*;
import io.vertx.core.spi.cluster.ClusterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 9.10.2018
 */

public class VerticleDeployHelper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(VerticleDeployHelper.class);
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
                LOGGER.error("Failed to deploy {} verticle!", name);
                promise.fail(res.cause());
            } else {
                LOGGER.info("{} verticle deployed!", name);
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
    @SuppressWarnings("unchecked")
    public static Promise<Void> deployHelper(final ClusterManager manager, final String className){

        final Promise<Void> promise = Promise.promise();
        final ClusterManager mgr = manager;
        final VertxOptions options = new VertxOptions().setClusterManager(mgr);

        Vertx.clusteredVertx(options, cluster -> {
            if (cluster.succeeded()) {
                try {
                    final Class clazz = Class.forName(className);
                    cluster.result().deployVerticle((Verticle) clazz.getDeclaredConstructor().newInstance(), res -> {
                        if(res.succeeded()){
                            LOGGER.info("Deployment id is {}", res.result());
                            promise.complete();
                        } else {
                            LOGGER.error("Deployment failed! ", res.cause());
                            promise.fail(res.cause());
                        }
                    });
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
                    LOGGER.error("Verticle deploy failed! ", e);
                }
            } else {
                LOGGER.error("Cluster up failed! ", cluster.cause());
                promise.fail(cluster.cause());
            }
        });

        return promise;
    }
}
