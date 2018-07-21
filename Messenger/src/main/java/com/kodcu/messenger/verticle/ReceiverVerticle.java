package com.kodcu.messenger.verticle;

/*
 * Created by hakdogan on 18.07.2018
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import static com.kodcu.util.Constants.*;

@Slf4j
public class ReceiverVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        final EventBus eventBus = vertx.eventBus();
        eventBus.consumer(ADDRESS, receivedMessage -> {
            log.debug("Received message: " + receivedMessage.body());
            receivedMessage.reply(DEFAULT_REPLY_MESSAGE);
        });

        log.info("Receiver ready!");
    }
}
