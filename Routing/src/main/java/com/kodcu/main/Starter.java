package com.kodcu.main;

import com.kodcu.helper.ClusterConfiguratorHelper;
import com.kodcu.routing.verticle.LimiterVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2019-05-25
 */

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
                cluster.result().deployVerticle(new LimiterVerticle(), res -> {
                    if(res.succeeded()){
                        log.info("Deployment id is: {} ", res.result());
                    } else {
                        log.error("Deployment failed!", res.cause());
                    }
                });
            } else {
                log.error("Cluster up failed: {} ", cluster.cause());
            }
        });
    }
}
