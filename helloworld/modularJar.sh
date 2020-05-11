#!/usr/bin/env bash

mkdir -p ../modularjars

jar --create --file ../modularjars/helloworld@1.0.jar \
--module-version=1.0 \
--main-class=com.kodcu.hello.main.Starter \
-C ../mods/helloworld .