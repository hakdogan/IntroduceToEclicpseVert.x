# Worker Verticle

This example shows the use of `worker verticle`. A word you send with the `HTTP Post` request over the `http://localhost:8080/get/:message` is saved to a text file that's located under `recources` directory.
                                                 
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
* JDK 8 or later
* Maven 3.0.0 or later

## To compile
```bash
mvn celan install
```

## To run
```bash
java -jar target/workerVerticleLauncher.jar -worker
```

Or

```bash
sh run.sh
```

## Relevant article is
[How to Run Blocking Code in Vert.x](https://medium.com/@hakdogan/how-to-run-blocking-code-in-vert-x-174dad7e0f94)