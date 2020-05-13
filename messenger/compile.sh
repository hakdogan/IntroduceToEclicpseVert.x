#!/usr/bin/env bash

#make sure the helper module is compiled before running this script

mvn clean install

javac -d ../mods/messenger --enable-preview --source 14 \
--module-path ../mods:modules \
src/main/java/module-info.java \
src/main/java/com/kodcu/nonclustered/messenger/verticle/MessengerLauncher.java \
src/main/java/com/kodcu/nonclustered/messenger/verticle/ReceiverVerticle.java \
src/main/java/com/kodcu/nonclustered/messenger/verticle/SenderVerticle.java \
src/main/java/com/kodcu/nonclustered/messenger/main/Starter.java \
