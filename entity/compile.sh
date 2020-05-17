#!/usr/bin/env bash

mvn clean install

javac -d ../mods/entity \
--module-path modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/entity/Bucket.java \
src/main/java/com/kodcu/entity/SimpleData.java \
src/main/java/com/kodcu/entity/StockExchange.java \
src/main/java/com/kodcu/entity/StockExchangeData.java
