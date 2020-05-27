#!/usr/bin/env bash

mkdir -p ../modularjars

jar --create --file ../modularjars/routing@2.0.jar \
--module-version=2.0 --main-class=com.kodcu.routing.main.Starter \
-C ../mods/routing .