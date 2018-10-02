package com.kodcu.clustered.receiver.verticle;

/*
 * Created by hakdogan on 27.07.2018
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import static com.kodcu.util.Constants.*;

@Slf4j
public class ClusteredReceiver extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        final EventBus eventBus = vertx.eventBus();
        eventBus.consumer(ADDRESS, receivedMessage -> log.debug("Received message: {} ", receivedMessage.body()));
        log.info("Receiver ready!");
    }
}
