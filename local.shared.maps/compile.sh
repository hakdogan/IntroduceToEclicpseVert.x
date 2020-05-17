#!/usr/bin/env bash

#make sure the entity and helper modules are compiled before running this script

mvn clean install

javac -d ../mods/local.shared.maps --enable-preview --source 14 \
--module-path ../mods:../entity/modules:../helper/modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/local/shared/maps/main/Starter.java \
src/main/java/com/kodcu/local/shared/maps/verticle/SharerAndReaderLauncher.java \
src/main/java/com/kodcu/local/shared/maps/verticle/SharerVerticle.java \
src/main/java/com/kodcu/local/shared/maps/verticle/ReaderVerticle.java