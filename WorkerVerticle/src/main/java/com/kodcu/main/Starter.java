package com.kodcu.main;

import com.kodcu.wv.verticle.WorkerVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import static com.kodcu.util.Constants.DEFAULT_WORKER_POOL_SIZE;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 1.10.2018
 */

@Slf4j
public class Starter
{
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
                log.info("Deployment id is: {} ", res.result());
            } else {
                log.info("Deployment failed!");
            }
        });
    }
}
