#!/usr/bin/env bash

#make sure the helper module is compiled before running this script

mvn clean install

javac -d ../mods/receiver --enable-preview --source 14 \
--module-path ../mods:modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/clustered/receiver/verticle/ClusteredReceiver.java \
src/main/java/com/kodcu/clustered/receiver/main/Starter.java