#!/usr/bin/env bash

javac -d ../mods/entity --enable-preview --source 14 \
src/main/java/module-info.java \
src/main/java/com/kodcu/entity/Bucket.java \
src/main/java/com/kodcu/entity/SimpleData.java \
src/main/java/com/kodcu/entity/StockExchange.java \
src/main/java/com/kodcu/entity/StockExchangeData.java
