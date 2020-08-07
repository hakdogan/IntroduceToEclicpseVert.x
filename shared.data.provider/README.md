# Shared Data Provider

This example in this module shows the use of `Shared Data` via `Asynchronous Shared Maps` in Vert.x. An object(`com.kodcu.entity.StockExchange`) saved with random values to the `Async Map` can be accessed from another verticle.
                                                 
```java
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
```

## Requirements
* JDK 9 or later
* Maven 3.0.0 or later
* Maven compiler plugin 3.8.0 or later

## To compile
```bash
sh compile.sh
```

## To create modular jar
```bash
sh modularJar.sh
```

## To run
java -p ../modularjars:../entity/modules:../helper/modules \
 --add-modules java.se \
 --add-exports java.base/jdk.internal.ref=com.hazelcast.core \
 --add-opens java.base/java.lang=com.hazelcast.core \
 --add-opens java.base/java.nio=com.hazelcast.core \
 --add-opens java.base/sun.nio.ch=com.hazelcast.core \
 --add-opens java.management/sun.management=com.hazelcast.core \
 --add-opens jdk.management/com.sun.management.internal=com.hazelcast.core \
-m shared.data.provider
```

Or

```bash
sh run.sh
```

## Relevant article is
[How to Share Data Between Threads in Vert.x](https://medium.com/@hakdogan/how-to-share-data-between-threads-in-vert-x-afdf26dcc684)
