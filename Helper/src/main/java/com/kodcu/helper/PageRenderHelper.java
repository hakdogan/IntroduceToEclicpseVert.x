package com.kodcu.helper;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;
import static com.kodcu.util.Constants.DEFAULT_CONTENT_TYPE;
import static com.kodcu.util.Constants.TEMPLATES_DIRECTORY;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 13.10.2018
 */

public class PageRenderHelper {

    private static final FreeMarkerTemplateEngine templateEngine = FreeMarkerTemplateEngine.create();

    private PageRenderHelper() {}

    /**
     *
     * @param routingContext
     * @param url
     * @param produceType
     * @param statusCode
     */
    public static void pageRender(RoutingContext routingContext, String url, String produceType, int statusCode){
        templateEngine.render(routingContext, TEMPLATES_DIRECTORY, url, page -> {
            if (page.succeeded()) {
                routingContext.response().putHeader(DEFAULT_CONTENT_TYPE, produceType)
                        .setStatusCode(statusCode)
                        .end(page.result());
            } else {
                routingContext.fail(page.cause());
            }
        });
    }
}
