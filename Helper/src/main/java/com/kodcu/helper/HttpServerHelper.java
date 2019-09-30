package com.kodcu.helper;

import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;

import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2.10.2018
 */

@Slf4j
public class HttpServerHelper
{

    private HttpServerHelper() {}

    /**
     *
     * @param vertx
     * @param router
     * @param config
     * @param promise
     */
    public static void createAnHttpServer(final Vertx vertx, final Router router, final JsonObject config, final Promise<Void> promise){
        createAnHttpServer(vertx, router, config, config.getInteger("http.port", DEFAULT_HTTP_PORT), promise);
    }

    /**
     *
     * @param vertx
     * @param router
     * @param config
     * @param port
     * @param promise
     */
    public static void createAnHttpServer(final Vertx vertx, final Router router, final JsonObject config,
                                          final int port, final Promise<Void> promise){
        vertx.createHttpServer().requestHandler(router)
                .listen(config.getInteger("http.port", port), result -> {
                    if (result.succeeded()) {
                        log.info("HTTP server running on port {} ", port);
                        promise.complete();
                    } else {
                        log.error("Could not start a HTTP server", result.cause());
                        promise.fail(result.cause());
                    }
                });
    }
}
