# Clustered Receiver

This example shows the use of `broadcasting messaging` in the `clustered environment`. The receiver verticle receives messages from the publisher that it's subscribed to.

```java
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        final EventBus eventBus = vertx.eventBus();
        eventBus.consumer(ADDRESS, receivedMessage -> {
            log.debug("Received message: {} ", receivedMessage.body());
        });

        log.info("Receiver ready!");
    }
    
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
java -jar target/clusteredReceiverLauncher.jar

```
Or

```bash
sh run.sh
```

![](images/receiver.png)

## Relevant article is
[How to Run a Vert.x Cluster With Broadcasting Messaging](https://medium.com/@hakdogan/how-to-run-a-vert-x-cluster-with-broadcasting-messaging-fc79ff113c9c)
