package com.kodcu.shared.data.reader.main;

import com.kodcu.helper.ClusterConfiguratorHelper;
import com.kodcu.shared.data.reader.verticle.ReaderVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 13.10.2018
 */

public class Starter {

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
                cluster.result().deployVerticle(new ReaderVerticle(), res -> {
                    if(res.succeeded()){
                        LOGGER.info("Deployment id is {} ", res.result());
                    } else {
                        LOGGER.error("Deployment failed!", res.cause());
                    }
                });
            } else {
                LOGGER.error("Cluster up failed!", cluster.cause());
            }
        });
    }
}
