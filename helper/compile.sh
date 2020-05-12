#!/usr/bin/env bash

mvn clean install

mkdir -p ../mods/util
mkdir -p ../mods/helper

javac -d ../mods/util \
../util/src/main/java/module-info.java \
../util/src/main/java/com/kodcu/util/Constants.java

javac -d ../mods/helper \
--module-path ../mods:../modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/helper/ClusterConfiguratorHelper.java \
src/main/java/com/kodcu/helper/HttpServerHelper.java \
src/main/java/com/kodcu/helper/RandomPortHelper.java \
src/main/java/com/kodcu/helper/RouterHelper.java \
src/main/java/com/kodcu/helper/SoapUIHelper.java \
src/main/java/com/kodcu/helper/VerticleDeployHelper.java