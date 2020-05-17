#!/usr/bin/env bash

mvn clean install

javac -d ../mods/util \
$(dirname "$0")/src/main/java/module-info.java \
$(dirname "$0")/src/main/java/com/kodcu/util/Constants.java