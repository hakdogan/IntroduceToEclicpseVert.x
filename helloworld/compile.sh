#!/usr/bin/env bash

chmod a+x ../util/compile.sh
chmod a+x ../util/modularJar.sh

../util/compile.sh
../util/modularJar.sh

mvn clean install

javac -d ../mods/helloworld --enable-preview --source 14 \
--module-path ../mods:modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/hello/main/Starter.java \
src/main/java/com/kodcu/hello/verticle/HelloWorldVerticle.java