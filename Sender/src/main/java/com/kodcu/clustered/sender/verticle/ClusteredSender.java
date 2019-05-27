package com.kodcu.clustered.sender.verticle;

/*
 * Created by hakdogan on 26.07.2018
 */

import com.kodcu.helper.HttpServerHelper;
import com.kodcu.helper.RouterHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import static com.kodcu.util.Constants.*;

@Slf4j
public class ClusteredSender extends AbstractVerticle
{

    /**
     *
     * @param future
     */
    @Override
    public void start(Future<Void> future) {
        final Router router = RouterHelper.createRouter(vertx, "Hello from clustered messenger example!");
        router.post("/sendForAll/:" + PATH_PARAM_TO_RECEIVE_MESSAGE).handler(this::sendMessageForAllReceivers);
        HttpServerHelper.createAnHttpServer(vertx, router, config(), future);
    }

    /**
     *
     * @param routingContext
     */
    private void sendMessageForAllReceivers(RoutingContext routingContext){
        final EventBus eventBus = vertx.eventBus();
        final String message = routingContext.request().getParam(PATH_PARAM_TO_RECEIVE_MESSAGE);

        eventBus.publish(ADDRESS, message);
        log.info("Current Thread Id {} Is Clustered {} ", Thread.currentThread().getId(), vertx.isClustered());
        routingContext.response().end(message);
    }
}
