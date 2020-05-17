# Hello World Module
The simple application in this module shows how to create an HTTP server and how to write to response.

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
java -p ../modularjars:../helper/modules -m helloworld
```
Or

```bash
sh run.sh
```

## Relevant article is
[Introduce to Eclipse Vert.x](https://medium.com/@hakdogan/introduce-to-eclicpse-vert-x-1d24c97643c7)