package com.kodcu.messenger.verticle;

/*
 * Created by hakdogan on 18.07.2018
 */

import com.kodcu.helper.HttpServerHelper;
import com.kodcu.helper.RouterHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import static com.kodcu.util.Constants.*;

@Slf4j
public class SenderVerticle extends AbstractVerticle {

    /**
     *
     * @param future
     * @throws Exception
     */
    @Override
    public void start(Future<Void> future) throws Exception {
        final Router router = RouterHelper.createRouter(vertx, "Hello from non-clustered messenger example!");
        router.post("/send/:" + PATH_PARAM_TO_RECEIVE_MESSAGE).handler(this::sendMessage);
        HttpServerHelper.createAnHttpServer(vertx, router, config(), future);
    }

    /**
     *
     * @param routingContext
     */
    private void sendMessage(RoutingContext routingContext){
        final EventBus eventBus = vertx.eventBus();
        final String message = routingContext.request().getParam(PATH_PARAM_TO_RECEIVE_MESSAGE);

        eventBus.send(ADDRESS, message, reply -> {
            if (reply.succeeded()) {
                log.info("Received reply: {} ", reply.result().body());
            } else {
                log.info("No reply");
            }
        });
        routingContext.response().end(message);
    }
}
