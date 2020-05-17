# Local Shared Maps Module

This example in this module shows the use of `Shared Data` via `Local Shared Maps` in Vert.x. An object(`com.kodcu.entity.SimpleData`) you send with the `HTTP Post` request over the `http://localhost:8080/put` is saved and you can access that object with `HTTP Get` request over the `http://localhost:8081/read` URL.
                                                 
```java
   /**
        *
        * @param routingContext
        */
       private void putData(RoutingContext routingContext){
           final SharedData sd = vertx.sharedData();
           final LocalMap<String, String> sharedData = sd.getLocalMap(DEFAULT_LOCAL_MAP_NAME);
           final Data data = Json.decodeValue(routingContext.getBodyAsString(), Data.class);
   
           sharedData.put(data.getKey(), data.getValue());
           routingContext.response()
                   .setStatusCode(201)
                   .putHeader("content-type", "application/json; charset=utf-8")
                   .end(Json.encodePrettily(data));
   
   
       }
```

```java
   /**
        *
        * @param routingContext
        */
       private void readData(RoutingContext routingContext){
           final SharedData sd = vertx.sharedData();
           final LocalMap<String, String> sharedData = sd.getLocalMap(DEFAULT_LOCAL_MAP_NAME);
   
           routingContext.response()
                   .setStatusCode(200)
                   .putHeader("content-type", "application/json; charset=utf-8")
                   .end(Json.encodePrettily(sharedData));
   
       }
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
```bash
java -p ../modularjars:../entity/modules:../helper/modules -m local.shared.maps
```
Or

```bash
sh run.sh
```

## Relevant article is
[How to Share Data Between Threads in Vert.x](https://medium.com/@hakdogan/how-to-share-data-between-threads-in-vert-x-afdf26dcc684)