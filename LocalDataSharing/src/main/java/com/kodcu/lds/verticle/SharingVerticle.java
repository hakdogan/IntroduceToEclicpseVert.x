package com.kodcu.lds.verticle;

import com.kodcu.entity.SimpleData;
import com.kodcu.helper.HttpServerHelper;
import com.kodcu.helper.RouterHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2018
 */

public class SharingVerticle extends AbstractVerticle
{
    /**
     *
     * @param promise
     */
    @Override
    public void start(Promise<Void> promise) {

        final Router router = RouterHelper.createRouter(vertx, "Hello from shared data example!");
        router.post("/put*").handler(BodyHandler.create());
        router.post("/put").handler(this::putData);

        HttpServerHelper.createAnHttpServer(vertx, router, config(), promise);
    }

    /**
     *
     * @param routingContext
     */
    private void putData(RoutingContext routingContext){
        final SharedData sd = vertx.sharedData();
        final LocalMap<String, String> sharedData = sd.getLocalMap(DEFAULT_LOCAL_MAP_NAME);
        final SimpleData data = Json.decodeValue(routingContext.getBodyAsString(), SimpleData.class);

        sharedData.put(data.getKey(), data.getValue());
        routingContext.response()
                .setStatusCode(201)
                .putHeader(CONTENT_TYPE, JSON_PRODUCE)
                .end(Json.encodePrettily(data));


    }
}
