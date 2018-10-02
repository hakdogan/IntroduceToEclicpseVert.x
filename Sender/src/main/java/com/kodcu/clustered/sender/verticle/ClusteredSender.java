package com.kodcu.clustered.sender.verticle;

/*
 * Created by hakdogan on 26.07.2018
 */

import com.kodcu.helper.HttpServerHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import static com.kodcu.util.Constants.*;

@Slf4j
public class ClusteredSender extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) throws Exception {

        final Router router = Router.router(vertx);
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/html").end("<h1>Hello from clustered messenger example!</h1>");
        });
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

        eventBus.publish(ADDRESS, message);
        log.info("Current Thread Id {} Is Clustered {} ", Thread.currentThread().getId(), vertx.isClustered());
        routingContext.response().end(message);
    }
}
