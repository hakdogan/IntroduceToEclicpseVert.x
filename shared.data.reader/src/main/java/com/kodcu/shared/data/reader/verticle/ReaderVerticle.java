package com.kodcu.shared.data.reader.verticle;

import com.kodcu.entity.StockExchange;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kodcu.helper.HttpServerHelper;
import com.kodcu.helper.RendererHelper;
import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 13.10.2018
 */

public class ReaderVerticle extends AbstractVerticle
{
    private final Logger logger = LoggerFactory.getLogger(ReaderVerticle.class);
    private static final String TEMPLATE_FILE_NAME = "/index.ftl";
    private StockExchange stockExchange;

    /**
     *
     * @param promise
     */
    @Override
    public void start(Promise<Void> promise) {
        final Router router = Router.router(vertx);
        router.get("/").handler(this::welcomePage);
        router.get("/refresh").handler(this::refresh);
        HttpServerHelper.createAnHttpServer(vertx, router, config(), promise);
    }

    /**
     *
     * @param routingContext
     */
    private void welcomePage(final RoutingContext routingContext){
        saveExchangeData();
        JsonObject contextObject = new JsonObject()
                .put("title", "Asynchronous Shared Data example")
                .put("headText", "Hello from Asynchronous Shared Data example!")
                .put("information", "This chart shows the rate values of some imaginary stocks at a specific time. " +
                "The values are generated randomly by the SharedMapsProvider verticle every three seconds and the SharedDataReader verticle read " +
                "these data every second");
        RendererHelper.callRenderPage(contextObject, routingContext, TEMPLATE_FILE_NAME, HTML_PRODUCE, HTTP_STATUS_CODE_OK);
    }

    /**
     *
     * @param routingContext
     */
    private void refresh(RoutingContext routingContext){
        saveExchangeData();
        routingContext.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(stockExchange));
    }

    private void saveExchangeData(){
        SharedData sharedData = vertx.sharedData();
        sharedData.<String, StockExchange>getAsyncMap(DEFAULT_ASYNC_MAP_NAME, res -> {
            if (res.succeeded()) {
                AsyncMap<String, StockExchange> stockExchangeAsyncMap = res.result();
                stockExchangeAsyncMap.get(DEFAULT_ASYNC_MAP_KEY, asyncDataResult -> {
                    stockExchange = asyncDataResult.result();
                    logger.debug("Stock Exchange object is {} ", Json.encodePrettily(stockExchange));
                });
            } else {
                logger.debug("Something went wrong when access to shared map!");
            }
        });
    }
}
