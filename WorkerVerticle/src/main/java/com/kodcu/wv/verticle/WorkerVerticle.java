package com.kodcu.wv.verticle;

import com.kodcu.helper.HttpServerHelper;
import com.kodcu.helper.RouterHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
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
public class WorkerVerticle extends AbstractVerticle
{

    /**
     *
     * @param promise
     */
    @Override
    public void start(Promise<Void> promise) {
        final Router router = RouterHelper.createRouter(vertx,"Hello from Worker Verticle example!");
        router.post("/get/:" + PATH_PARAM_TO_SAVE_WORD).handler(this::saveWord);
        HttpServerHelper.createAnHttpServer(vertx, router, config(), promise);
    }

    /**
     *
     * @param routingContext
     */
    private void saveWord(RoutingContext routingContext) {

        final String message = routingContext.request().getParam(PATH_PARAM_TO_SAVE_WORD);
        final ClassLoader classLoader = getClass().getClassLoader();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(classLoader.getResource(FILE_NAME).getFile()))){
            writer.write(message);
            log.info("The word was written: {} ", message);
            routingContext.response().end(message);
        } catch (Exception ex) {
            log.error("Failed to save word {} ", ex);
        }

    }
}
