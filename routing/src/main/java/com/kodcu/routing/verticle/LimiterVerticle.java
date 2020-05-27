package com.kodcu.routing.verticle;

import com.kodcu.helper.HttpServerHelper;
import com.kodcu.helper.RouterHelper;
import com.kodcu.routing.handler.CircuitBreakerHandler;
import com.kodcu.routing.handler.RateLimiterHandler;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2019-05-25
 */

public class LimiterVerticle extends AbstractVerticle
{
    /**
     *
     * @param promise
     */
    @Override
    public void start(Promise<Void> promise) {

        final ConfigStoreOptions rateOptions = new ConfigStoreOptions()
                .setType("file")
                .setOptional(true)
                .setConfig(new JsonObject().put("path", System.getProperty("user.dir") + "/src/main/resources/config.json"));

        final ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(rateOptions);
        final Router router = RouterHelper.createRouter(vertx, "Hello from routing example!");

        router.get("/limiting").handler(new CircuitBreakerHandler(options));
        router.get("/limiting").handler(new RateLimiterHandler(options));
        HttpServerHelper.createAnHttpServer(vertx, router, config(), promise);
    }
}
