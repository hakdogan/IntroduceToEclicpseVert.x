package com.kodcu.messenger.verticle;

/*
 * Created by hakdogan on 18.07.2018
 */

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

    private final int HTTP_PORT = 8080;
    /**
     *
     * @param future
     * @throws Exception
     */
    @Override
    public void start(Future<Void> future) throws Exception {

        final Router router = Router.router(vertx);
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/html").end("<h1>Hello from non-clustered messenger example!</h1>");
        });
        router.post("/send/:message").handler(this::sendMessage);

        vertx.createHttpServer().requestHandler(router::accept)
                .listen(config().getInteger("http.server.port", HTTP_PORT), result -> {
                    if (result.succeeded()) {
                        log.info("HTTP server running on port " + HTTP_PORT);
                        future.complete();
                    } else {
                        log.error("Could not start a HTTP server", result.cause());
                        future.fail(result.cause());
                    }
                });
    }

    /**
     *
     * @param routingContext
     */
    private void sendMessage(RoutingContext routingContext){
        final EventBus eventBus = vertx.eventBus();
        final String message = routingContext.request().getParam(PATH_PARAM);

        eventBus.send(ADDRESS, message, reply -> {
            if (reply.succeeded()) {
                log.info("Received reply: " + reply.result().body());
            } else {
                log.info("No reply");
            }
        });
        routingContext.response().end(message);
    }
}
