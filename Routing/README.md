# Routing

This example shows how to `route` the `router` to the next matching route.

```java
if (!circuitbreaker) {
    context.next();
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
java -jar target/routingLauncher.jar

```
Or

```bash
sh run.sh
```