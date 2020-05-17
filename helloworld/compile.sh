#!/usr/bin/env bash

#make sure the helper module is compiled before running this script

mvn clean install

javac -d ../mods/helloworld \
--module-path ../mods:../helper/modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/hello/main/Starter.java \
src/main/java/com/kodcu/hello/verticle/HelloWorldVerticle.java