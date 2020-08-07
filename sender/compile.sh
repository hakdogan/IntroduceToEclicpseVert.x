#!/usr/bin/env bash

#make sure the helper module is compiled before running this script

mvn clean install

javac -d ../mods/sender \
--module-path ../mods:../helper/modules:modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/clustered/sender/verticle/ClusteredSender.java \
src/main/java/com/kodcu/clustered/sender/main/Starter.java