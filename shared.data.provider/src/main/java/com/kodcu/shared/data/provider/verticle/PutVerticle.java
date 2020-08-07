package com.kodcu.shared.data.provider.verticle;

import com.kodcu.entity.StockExchange;
import com.kodcu.entity.StockExchangeData;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import static com.kodcu.util.Constants.*;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 12.10.2018
 */

public class PutVerticle extends AbstractVerticle
{
    private final Logger logger = LoggerFactory.getLogger(PutVerticle.class);

    @Override
    public void start() throws NoSuchAlgorithmException {

        final Random random = SecureRandom.getInstanceStrong();
        final SharedData sharedData = vertx.sharedData();

        vertx.setPeriodic(3000, h ->
            sharedData.<String, StockExchange>getAsyncMap(DEFAULT_ASYNC_MAP_NAME, res -> {
                if (res.succeeded()) {
                    AsyncMap<String, StockExchange> myAsyncMap = res.result();
                    myAsyncMap.get(DEFAULT_ASYNC_MAP_KEY, asyncDataResult -> {

                        LocalDateTime dateTime = LocalDateTime.now();
                        StockExchange stockExchange = new StockExchange(String.join(":", String.valueOf(dateTime.getHour()),
                                String.valueOf(dateTime.getMinute()), String.valueOf(dateTime.getSecond())),
                                Arrays.asList(new StockExchangeData(KODCU_STOCK_NAME, random.nextInt(100)),
                                        new StockExchangeData(JUG_IST_STOCK_NAME, random.nextInt(100))));

                        myAsyncMap.put(DEFAULT_ASYNC_MAP_KEY, stockExchange, resPut -> {
                            if (resPut.succeeded()) {
                                logger.info("Added data into the map {} ", Json.encodePrettily(stockExchange));
                            } else {
                                logger.debug("Failed to add data {} ", Json.encodePrettily(stockExchange));
                            }
                        });
                    });
                } else {
                    logger.debug("Failed to get map!");
                }
            }));
    }
}
