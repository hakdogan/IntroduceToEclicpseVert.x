#!/usr/bin/env bash

#make sure the util module is compiled before running this script

mvn clean install

javac -Xlint:deprecation -d ../mods/routing \
--module-path ../mods:../helper/modules:../entity/modules:modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/routing/handler/CircuitBreakerHandler.java \
src/main/java/com/kodcu/routing/handler/RateLimiterHandler.java \
src/main/java/com/kodcu/routing/main/Starter.java \
src/main/java/com/kodcu/routing/verticle/LimiterVerticle.java