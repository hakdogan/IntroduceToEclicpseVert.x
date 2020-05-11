#!/usr/bin/env bash

mvn clean install

mkdir -p ../mods/util
mkdir -p ../mods/helloworld

javac -d ../mods/util \
../util/src/main/java/module-info.java \
../util/src/main/java/com/kodcu/util/Constants.java

javac -d ../mods/helloworld \
--module-path ../mods:../dependencies \
src/main/java/module-info.java \
src/main/java/com/kodcu/hello/main/Starter.java \
src/main/java/com/kodcu/hello/verticle/HelloWorldVerticle.java