package com.kodcu.nonclustered.messenger.verticle;

/*
 * Created by hakdogan on 18.07.2018
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

public class SenderVerticle extends AbstractVerticle
{
    private final Logger logger = LoggerFactory.getLogger(SenderVerticle.class);

    /**
     *
     * @param promise
     */
    @Override
    public void start(Promise<Void> promise) {
        final Router router = RouterHelper.createRouter(vertx, "Hello from non-clustered messenger example!");
        router.post("/send/:" + PATH_PARAM_TO_RECEIVE_MESSAGE).handler(this::sendMessage);
        HttpServerHelper.createAnHttpServer(vertx, router, config(), promise);
    }

    /**
     *
     * @param routingContext
     */
    private void sendMessage(RoutingContext routingContext){
        final EventBus eventBus = vertx.eventBus();
        final String message = routingContext.request().getParam(PATH_PARAM_TO_RECEIVE_MESSAGE);

        eventBus.request(ADDRESS, message, reply -> {
            if (reply.succeeded()) {
                logger.info("Received reply: {}", reply.result().body());
            } else {
                logger.info("No reply");
            }
        });
        routingContext.response().end(message);
    }
}
