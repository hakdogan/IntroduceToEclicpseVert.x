# Messenger Module

This example in this module shows the use of 2 verticles communicating with each other. The messages you send with the `HTTP Post` requests over `the http://localhost:8080/send/:message` are forwarded to the receiver verticle.

## Sender
```java
   /**
     *
     * @param routingContext
     */
    private void sendMessage(RoutingContext routingContext){
        final EventBus eventBus = vertx.eventBus();
        final String message = routingContext.request().getParam(PATH_PARAM);

        eventBus.send(ADDRESS, message, reply -> {
            if (reply.succeeded()) {
                log.info("Received reply: " + reply.result().body());
            } else {
                log.info("No reply");
            }
        });
        routingContext.response().end(message);
    }
```

## Receiver
```java
    @Override
    public void start() throws Exception {
        final EventBus eventBus = vertx.eventBus();
        eventBus.consumer(ADDRESS, receivedMessage -> {
            log.debug("Received message: " + receivedMessage.body());
            receivedMessage.reply(DEFAULT_REPLY_MESSAGE);
        });

        log.info("Receiver ready!");
    }
```

## Requirements
* JDK 14
* Maven 3.0.0 or later
* Maven compiler plugin 3.8.0 or later
* Maven surefire plugin 

## To compile
```bash
sh compile.sh
```

## To create modular jar
```bash
sh modularJar.sh
```

## To run
```bash
java --enable-preview -p ../modularjars:../helper/modules -m messenger
```
Or

```bash
sh run.sh
```

![](images/vertx.gif)

## Relevant article is
[How to Work With Multiple Verticles and Communication in Vert.x](https://medium.com/@hakdogan/working-with-multiple-verticles-and-communication-between-them-in-vert-x-2ed07e8e6425)
