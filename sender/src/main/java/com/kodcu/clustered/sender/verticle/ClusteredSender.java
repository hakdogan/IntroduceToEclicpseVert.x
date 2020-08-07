package com.kodcu.clustered.sender.verticle;

/*
 * Created by hakdogan on 26.07.2018
 */

import com.kodcu.helper.HttpServerHelper;
import com.kodcu.helper.RouterHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import static com.kodcu.util.Constants.*;


public class ClusteredSender extends AbstractVerticle
{

    private final Logger logger = LoggerFactory.getLogger(ClusteredSender.class);

    /**
     *
     * @param promise
     */
    @Override
    public void start(Promise<Void> promise) {
        final Router router = RouterHelper.createRouter(vertx, "Hello from clustered messenger example!");
        router.post("/sendForAll/:" + PATH_PARAM_TO_RECEIVE_MESSAGE).handler(this::sendMessageForAllReceivers);
        HttpServerHelper.createAnHttpServer(vertx, router, config(), promise);
    }

    /**
     *
     * @param routingContext
     */
    private void sendMessageForAllReceivers(RoutingContext routingContext){
        final EventBus eventBus = vertx.eventBus();
        final String message = routingContext.request().getParam(PATH_PARAM_TO_RECEIVE_MESSAGE);
        eventBus.publish(ADDRESS, message);
        logger.info("Current Thread Id {} Is Clustered {} ",
                Thread.currentThread().getId(), vertx.isClustered());
        routingContext.response().end(message);
    }
}
