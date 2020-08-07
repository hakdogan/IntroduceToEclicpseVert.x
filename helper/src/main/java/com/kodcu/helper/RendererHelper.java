package com.kodcu.helper;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.kodcu.util.Constants.CONTENT_TYPE;
import static com.kodcu.util.Constants.DEFAULT_TEMPLATES_DIRECTORY;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 15.05.2020
 **/
public class RendererHelper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RendererHelper.class);
    private static FreeMarkerTemplateEngine engine;

    private RendererHelper(){}

    /**
     *
     * @param contextObject
     * @param routingContext
     * @param templateFileName
     * @param produceType
     * @param statusCode
     */
    public static void callRenderPage(final JsonObject contextObject, final RoutingContext routingContext,
                                  final String templateFileName, final String produceType, final int statusCode){

        engine = FreeMarkerTemplateEngine.create(routingContext.vertx());
        engine.render(contextObject, DEFAULT_TEMPLATES_DIRECTORY + templateFileName, render -> {
            if (render.succeeded()) {
                routingContext.response().putHeader(CONTENT_TYPE, produceType)
                        .setStatusCode(statusCode)
                        .end(render.result());
            } else {
                LOGGER.error("An exception was thrown in callRenderPage method!", render.cause());
                routingContext.fail(render.cause());
            }
        });
    }
}
