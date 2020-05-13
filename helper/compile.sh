#!/usr/bin/env bash

chmod a+x ../util/compile.sh
chmod a+x ../util/modularJar.sh

../util/compile.sh
../util/modularJar.sh

mvn clean install

javac -d ../mods/helper --enable-preview --source 14 \
--module-path ../mods:modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/helper/ClusterConfiguratorHelper.java \
src/main/java/com/kodcu/helper/HttpServerHelper.java \
src/main/java/com/kodcu/helper/RandomPortHelper.java \
src/main/java/com/kodcu/helper/RouterHelper.java \
src/main/java/com/kodcu/helper/SoapUIHelper.java \
src/main/java/com/kodcu/helper/VerticleDeployHelper.java