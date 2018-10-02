package com.kodcu.helper;

import io.vertx.core.Future;
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
public class HttpServerHelper {

    private HttpServerHelper() {}

    public static void createAnHttpServer(Vertx vertx, Router router, JsonObject config, Future<Void> future){
        vertx.createHttpServer().requestHandler(router::accept)
                .listen(config.getInteger("http.server.port", DEFAULT_HTTP_PORT), result -> {
                    if (result.succeeded()) {
                        log.info("HTTP server running on port {} ", DEFAULT_HTTP_PORT);
                        future.complete();
                    } else {
                        log.error("Could not start a HTTP server", result.cause());
                        future.fail(result.cause());
                    }
                });

    }
}
