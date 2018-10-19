# Shared Data Provider

This example shows the use of `Shared Data` via `Asynchronous Shared Maps` in Vert.x. An object(`com.kodcu.entity.StockExchange`) saved with random values to the `Async Map` can be accessed from another verticle.
                                                 
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
                                log.info("Added data into the map {} ", Json.encodePrettily(stockExchange));
                            } else {
                                log.debug("Failed to add data {} ", Json.encodePrettily(stockExchange));
                            }
                        });
                    });
                } else {
                    log.debug("Failed to get map!");
                }
            }));
```

## Requirements
* JDK 8 or later
* Maven 3.0.0 or later

## To compile
```bash
mvn celan install
```

## To run
```bash
java -jar target/asyncPutVerticle.jar -cluster
```

Or

```bash
sh run.sh
```