package com.kodcu.nonclustered.messenger.verticle;

/*
 * Created by hakdogan on 18.07.2018
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.kodcu.util.Constants.*;

public class ReceiverVerticle extends AbstractVerticle
{
    private final Logger logger = LoggerFactory.getLogger(ReceiverVerticle.class);

    @Override
    public void start() {
        final EventBus eventBus = vertx.eventBus();
        eventBus.consumer(ADDRESS, receivedMessage -> {
            logger.debug("Received message: {}", receivedMessage.body());
            receivedMessage.reply(DEFAULT_REPLY_MESSAGE);
        });

        logger.info("Receiver ready!");
    }
}
