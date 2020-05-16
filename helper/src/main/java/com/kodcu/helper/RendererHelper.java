package com.kodcu.helper;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static com.kodcu.util.Constants.CONTENT_TYPE;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 15.05.2020
 **/

public class RendererHelper
{
    public static void callRenderPage(final JsonObject contextObject, final RoutingContext routingContext,
                                  final String templateFileName, final String produceType, final int statusCode){

        //Renderer.renderPage(contextObject, templateFileName);
        routingContext.response().putHeader(CONTENT_TYPE, produceType)
                        .setStatusCode(statusCode)
                        .end(contextObject.encodePrettily());

    }
}
