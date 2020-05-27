# Routing Module

This example in this module shows how to `route` the `router` to the next matching route. For demonstration, `SoapUI` was used in the test object.

```java
if (!circuitbreaker) {
    context.next();
}
```

Test result from the console
```text
23:23:47,131 DEBUG [SoapUIMultiThreadedHttpConnectionManager$SoapUIDefaultClientConnection] Sending request: GET /limiting HTTP/1.1
May 27, 2020 11:23:47 PM com.kodcu.routing.handler.RateLimiterHandler
INFO: Request allowed...
23:23:47,374 DEBUG [SoapUIMultiThreadedHttpConnectionManager$SoapUIDefaultClientConnection] Receiving response: HTTP/1.1 200 OK

...

23:23:56,046 DEBUG [SoapUIMultiThreadedHttpConnectionManager$SoapUIDefaultClientConnection] Sending request: GET /limiting HTTP/1.1
May 27, 2020 11:23:56 PM com.kodcu.routing.handler.RateLimiterHandler
INFO: Request rejected...
23:23:56,070 DEBUG [SoapUIMultiThreadedHttpConnectionManager$SoapUIDefaultClientConnection] Receiving response: HTTP/1.1 429 Too Many Requests
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
java -p ../modularjars:../entity/modules:../helper/modules:modules -m routing
```
Or

```bash
sh run.sh
```

## Relevant article is

[How to Call the Next Handler in Vert.x?](https://medium.com/@hakdogan/how-to-call-the-next-handler-in-vert-x-c498506c427c)