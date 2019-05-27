package com.kodcu.routing.handler;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2019-05-25
 */

@Slf4j
public class CircuitBreakerHandler implements Handler<RoutingContext> {

    private final ConfigRetrieverOptions options;

    /**
     * @param options
     */
    public CircuitBreakerHandler(ConfigRetrieverOptions options) {
        this.options = options;
    }

    /**
     * @param context
     */
    @Override
    public void handle(RoutingContext context) {

        final ConfigRetriever retriever = ConfigRetriever.create(context.vertx(), options);

        retriever.getConfig(ar -> {
            if (ar.failed()) {
                log.error("Failed to retrieve the configuration");
            } else {

                final JsonObject config = ar.result();
                final boolean circuitbreaker = config.getBoolean("circuitbreaker");

                if (!circuitbreaker) {
                    context.next();
                } else {
                    context.response()
                            .putHeader(CONTENT_TYPE, HTML_PRODUCE)
                            .setStatusCode(HTTP_SERVICE_UNAVAILABLE)
                            .end("Service unavailable!");
                }
            }
        });

    }
}
