#!/usr/bin/env bash

mkdir -p ../modularjars

jar --create --file ../modularjars/local.shared.maps@2.0.jar \
--module-version=2.0 --main-class=com.kodcu.local.shared.maps.main.Starter \
-C ../mods/local.shared.maps .