# Worker Verticle

This example shows the use of `worker verticle`. A word you send with the `HTTP Post` request over the `http://localhost:8080/get/:message` is saved to a text file that's located under `recources` directory.
                                                 
```java
/**
     *
     * @param routingContext
     */
    private void saveWord(RoutingContext routingContext) {
        final String message = routingContext.request().getParam(PATH_PARAM_TO_SAVE_WORD);
        try {
            final BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_AND_NAME));
            writer.write(message);
            writer.close();
            log.info("The word was written: {} ", message);
            routingContext.response().end(message);
        } catch (Exception ex) {
            log.error("Failed to save word {} ", ex);
        }
    }
```

## Requirements
* JDK 8 or later
* Maven 3.0.0 or later

## To compile
```
mvn celan install
```

## To run
```
java -jar WorkerVerticle/target/workerVerticleLauncher.jar
```
Or

```
sh run.sh
```