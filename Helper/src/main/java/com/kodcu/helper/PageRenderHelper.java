package com.kodcu.helper;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import static com.kodcu.util.Constants.CONTENT_TYPE;
import static com.kodcu.util.Constants.DEFAULT_TEMPLATES_DIRECTORY;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 13.10.2018
 */

@Slf4j
public class PageRenderHelper
{
    private static final FreeMarkerTemplateEngine templateEngine = FreeMarkerTemplateEngine.create(Vertx.vertx());
    private PageRenderHelper() {}

    /**
     *
     * @param contextObject
     * @param routingContext
     * @param templateFileName
     * @param produceType
     * @param statusCode
     */
    public static void pageRender(final JsonObject contextObject, final RoutingContext routingContext,
                                  final String templateFileName, final String produceType, final int statusCode){
        templateEngine.render(contextObject, DEFAULT_TEMPLATES_DIRECTORY + templateFileName, page -> {
            if (page.succeeded()) {
                routingContext.response().putHeader(CONTENT_TYPE, produceType)
                        .setStatusCode(statusCode)
                        .end(page.result());
            } else {
                log.error("An exception was thrown in pageRender method!", page.cause());
                routingContext.fail(page.cause());
            }
        });
    }
}
