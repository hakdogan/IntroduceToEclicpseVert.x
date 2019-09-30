package com.kodcu.lds.verticle;

import com.kodcu.helper.HttpServerHelper;
import com.kodcu.helper.RouterHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2018
 */

public class ReaderVerticle extends AbstractVerticle
{
    /**
     *
     * @param promise
     */
    @Override
    public void start(Promise<Void> promise) {
        final Router router = RouterHelper.createRouter(vertx, "Hello from read data example!");
        router.get("/read").handler(this::readData);

        HttpServerHelper.createAnHttpServer(vertx, router, config(), 8081, promise);
    }

    /**
     *
     * @param routingContext
     */
    private void readData(RoutingContext routingContext){
        final SharedData sd = vertx.sharedData();
        final LocalMap<String, String> sharedData = sd.getLocalMap(DEFAULT_LOCAL_MAP_NAME);

        routingContext.response()
                .setStatusCode(HTTP_STATUS_CODE_OK)
                .putHeader(CONTENT_TYPE, JSON_PRODUCE)
                .end(Json.encodePrettily(sharedData));

    }
}
