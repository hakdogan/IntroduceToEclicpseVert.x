package com.kodcu.wv.verticle;

import com.kodcu.helper.HttpServerHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;

import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 1.10.2018
 */

@Slf4j
public class WorkerVerticle extends AbstractVerticle {

    /**
     *
     * @param future
     */
    @Override
    public void start(Future<Void> future)  {

        final Router router = Router.router(vertx);
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/html").end("<h1>Hello from Worker Verticle example!</h1>");
        });
        router.post("/get/:" + PATH_PARAM_TO_SAVE_WORD).handler(this::saveWord);
        HttpServerHelper.createAnHttpServer(vertx, router, config(), future);
    }

    /**
     *
     * @param routingContext
     */
    private void saveWord(RoutingContext routingContext) {
        final String message = routingContext.request().getParam(PATH_PARAM_TO_SAVE_WORD);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_AND_NAME))){
            writer.write(message);
            log.info("The word was written: {} ", message);
            routingContext.response().end(message);
        } catch (Exception ex) {
            log.error("Failed to save word {} ", ex);
        }
    }
}
