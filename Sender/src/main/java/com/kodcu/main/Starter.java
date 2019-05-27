package com.kodcu.main;

/*
 * Created by hakdogan on 26.07.2018
 */

import com.kodcu.clustered.sender.verticle.ClusteredSender;
import com.kodcu.helper.ClusterConfiguratorHelper;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Starter
{

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        final ClusterManager mgr = new HazelcastClusterManager(ClusterConfiguratorHelper.getHazelcastConfiguration());
        final VertxOptions options = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(options, cluster -> {
            if (cluster.succeeded()) {
                cluster.result().deployVerticle(new ClusteredSender(), res -> {
                    if(res.succeeded()){
                        log.info("Deployment id is: {} ", res.result());
                    } else {
                        log.error("Deployment failed!");
                    }
                });
            } else {
                log.error("Cluster up failed: {} ", cluster.cause());
            }
        });
    }
}
