package com.kodcu.clustered.receiver.verticle;

/*
 * Created by hakdogan on 27.07.2018
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import static com.kodcu.util.Constants.ADDRESS;

public class ClusteredReceiver extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(ClusteredReceiver.class);

    @Override
    public void start() {
        final EventBus eventBus = vertx.eventBus();
        eventBus.consumer(ADDRESS, receivedMessage -> logger.info("Received message: " + receivedMessage.body()));
        logger.info("Receiver ready!");
    }
}
