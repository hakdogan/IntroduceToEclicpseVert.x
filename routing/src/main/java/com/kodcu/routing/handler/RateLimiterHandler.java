package com.kodcu.routing.handler;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.kodcu.entity.Bucket;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2019-05-25
 */

public class RateLimiterHandler implements Handler<RoutingContext> {

    private final Logger logger = LoggerFactory.getLogger(RateLimiterHandler.class);
    private final ConfigRetrieverOptions options;
    private static final String SHAREDMAPNAME = "myLimiter";

    /**
     *
     * @param options
     */
    public RateLimiterHandler(ConfigRetrieverOptions options) {
        this.options = options;
    }

    /**
     *
     * @param context
     */
    @Override
    public void handle(RoutingContext context) {

        final ConfigRetriever retriever = ConfigRetriever.create(context.vertx(), options);
        retriever.getConfig(ar -> {
            if (ar.failed()) {
                logger.error("Failed to retrieve the configuration");
            } else {
                try {
                    Bucket limiter;
                    final JsonObject config = ar.result();
                    final Set<HazelcastInstance> instances = Hazelcast.getAllHazelcastInstances();
                    final HazelcastInstance hz = instances.stream().findFirst().get();
                    final IMap<String, Bucket> rateLimiterIMap = hz.getMap(SHAREDMAPNAME);

                    if (rateLimiterIMap.isEmpty()) {
                        limiter = new Bucket();
                        limiter.setBucketKey(config.getString("bucketKey"));
                        limiter.setToken(config.getInteger("ratelimit"));
                    } else {
                        limiter = rateLimiterIMap.get(SHAREDMAPNAME);
                        limiter.setToken(limiter.getToken() - 1);
                        if (limiter.getToken() < 1) {
                            logger.info("Request rejected...");
                            context.response()
                                    .putHeader(CONTENT_TYPE, HTML_PRODUCE)
                                    .setStatusCode(HTTP_TOO_MANY_REQUESTS)
                                    .end("Too many requests");
                            return;
                        }
                    }

                    rateLimiterIMap.put(SHAREDMAPNAME, limiter, 60, TimeUnit.MINUTES);
                    logger.info("Request allowed...");
                    context.response()
                            .putHeader(CONTENT_TYPE, HTML_PRODUCE)
                            .setStatusCode(HTTP_STATUS_CODE_OK)
                            .end("Welcome!");

                } catch (Exception ex){
                    context.response()
                            .putHeader(CONTENT_TYPE, HTML_PRODUCE)
                            .setStatusCode(INTERNEL_SERVER_ERROR_CODE)
                            .end(ex.toString());
                }
            }
        });
    }
}
