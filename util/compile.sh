#!/usr/bin/env bash

javac -d ../mods/util --enable-preview --source 14 \
$(dirname "$0")/src/main/java/module-info.java \
$(dirname "$0")/src/main/java/com/kodcu/util/Constants.java