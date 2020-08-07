#!/usr/bin/env bash

#make sure the entity and helper modules are compiled before running this script

mvn clean install

javac -d ../mods/shared.data.provider \
--module-path ../mods:../entity/modules:../helper/modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/shared/data/provider/main/Starter.java \
src/main/java/com/kodcu/shared/data/provider/verticle/PutVerticle.java
