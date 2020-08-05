#!/usr/bin/env bash

#make sure the entity and helper modules are compiled before running this script

mvn clean install

javac -d ../mods/shared.data.reader \
--module-path ../mods:../entity/modules:../helper/modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/shared/data/reader/main/Starter.java \
src/main/java/com/kodcu/shared/data/reader/verticle/ReaderVerticle.java
