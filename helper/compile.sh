#!/usr/bin/env bash

#make sure the util module is compiled before running this script

mvn clean install

javac -Xlint:deprecation -d ../mods/helper --module-path ../mods:modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/helper/ClusterConfiguratorHelper.java \
src/main/java/com/kodcu/helper/HttpServerHelper.java \
src/main/java/com/kodcu/helper/RandomPortHelper.java \
src/main/java/com/kodcu/helper/RendererHelper.java \
src/main/java/com/kodcu/helper/RouterHelper.java \
src/main/java/com/kodcu/helper/VerticleDeployHelper.java