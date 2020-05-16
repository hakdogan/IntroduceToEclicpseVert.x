# Worker Module

This example in this module shows the use of `Worker Verticle`. A word you send with the `HTTP Post` request over the `http://localhost:8080/get/:message` is saved to a text file that's located under `recources` directory.
                                                 
```java
   final Vertx vertx = Vertx.vertx();
        final DeploymentOptions options = new DeploymentOptions().setWorker(true)
                .setWorkerPoolSize(DEFAULT_WORKER_POOL_SIZE);
        vertx.deployVerticle(new WorkerVerticle(), options, res -> {
            if (res.succeeded()) {
                log.info("Deployment id is: " + res.result());
            } else {
                log.info("Deployment failed!");
            }
        });
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
java --enable-preview -p ../modularjars:../helper/modules -m worker
```
Or

```bash
sh run.sh
```

## Relevant article is
[How to Run Blocking Code in Vert.x](https://medium.com/@hakdogan/how-to-run-blocking-code-in-vert-x-174dad7e0f94)