#!/usr/bin/env bash

mkdir -p ../modularjars

jar --create --file ../modularjars/helloworld@2.0.jar \
--module-version=2.0 \
--main-class=com.kodcu.hello.main.Starter \
-C ../mods/helloworld .