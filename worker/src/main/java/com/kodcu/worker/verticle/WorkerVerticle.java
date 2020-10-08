package com.kodcu.worker.verticle;

import com.kodcu.helper.HttpServerHelper;
import com.kodcu.helper.RouterHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 1.10.2018
 */

public class WorkerVerticle extends AbstractVerticle
{

    private final Logger logger = LoggerFactory.getLogger(WorkerVerticle.class);

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

        final String message = routingContext.request()
                .getParam(PATH_PARAM_TO_SAVE_WORD).replaceAll("[\n|\r\t]", "_");

        try {
            Path path = Paths.get(FILE_NAME);
            Files.writeString(path, message, StandardOpenOption.TRUNCATE_EXISTING);
            logger.info("The word was written: {}", message);
            routingContext.response().end(message);
        }catch (Exception ex){
            logger.error("Failed to save word!");
            routingContext.response().end("Failed to save word!");
        }
    }
}
