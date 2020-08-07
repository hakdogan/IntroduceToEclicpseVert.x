package com.kodcu.worker.main;

import com.kodcu.worker.verticle.WorkerVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.kodcu.util.Constants.DEFAULT_WORKER_POOL_SIZE;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 1.10.2018
 */

public class Starter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Starter.class);

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        final Vertx vertx = Vertx.vertx();
        final DeploymentOptions options = new DeploymentOptions().setWorker(true)
                .setWorkerPoolSize(DEFAULT_WORKER_POOL_SIZE);
        vertx.deployVerticle(new WorkerVerticle(), options, res -> {
            if (res.succeeded()) {
                LOGGER.info("Deployment id is: {}", res.result());
            } else {
                LOGGER.info("Deployment failed! ", res.cause());
            }
        });
    }
}
