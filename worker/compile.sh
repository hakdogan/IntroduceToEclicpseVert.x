#!/usr/bin/env bash

#make sure the helper module is compiled before running this script

mvn clean install

javac -d ../mods/worker \
--module-path ../mods:../helper/modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/worker/verticle/WorkerVerticle.java \
src/main/java/com/kodcu/worker/main/Starter.java
