package com.kodcu.routing.main;

import com.kodcu.helper.ClusterConfiguratorHelper;
import com.kodcu.routing.verticle.LimiterVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2019-05-25
 */

public class Starter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Starter.class);
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        final ClusterManager mgr = new HazelcastClusterManager(ClusterConfiguratorHelper.getHazelcastConfiguration());
        final VertxOptions options = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(options, cluster -> {
            if (cluster.succeeded()) {
                cluster.result().deployVerticle(new LimiterVerticle(), res -> {
                    if(res.succeeded()){
                        LOGGER.info("Deployment id is: " + res.result());
                    } else {
                        LOGGER.error("Deployment failed! " + res.cause());
                    }
                });
            } else {
                LOGGER.error("Cluster up failed: " + cluster.cause());
            }
        });
    }
}
