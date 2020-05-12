package com.kodcu.helper;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

import static com.kodcu.util.Constants.CONTENT_TYPE;
import static com.kodcu.util.Constants.HTML_PRODUCE;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2018
 */

public class RouterHelper
{
    private RouterHelper(){}

    /**
     *
     * @param vertx
     * @param welcomeMessage
     * @return
     */
    public static Router createRouter(final Vertx vertx, final String welcomeMessage){
        final Router router = Router.router(vertx);
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader(CONTENT_TYPE, HTML_PRODUCE).end("<h1>" + welcomeMessage + "</h1>");
        });
        return router;
    }
}
