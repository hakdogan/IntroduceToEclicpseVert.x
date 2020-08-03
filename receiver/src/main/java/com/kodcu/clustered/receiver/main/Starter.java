package com.kodcu.clustered.receiver.main;

/*
 * Created by hakdogan on 27.07.2018
 */

import com.kodcu.clustered.receiver.verticle.ClusteredReceiver;
import com.kodcu.helper.ClusterConfiguratorHelper;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

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
                cluster.result().deployVerticle(new ClusteredReceiver(), res -> {
                    if(res.succeeded()){
                        LOGGER.info("Deployment id is: {}", res.result());
                    } else {
                        LOGGER.error("Deployment failed!");
                    }
                });
            } else {
                LOGGER.error("Cluster up failed: ", cluster.cause());
            }
        });
    }
}
