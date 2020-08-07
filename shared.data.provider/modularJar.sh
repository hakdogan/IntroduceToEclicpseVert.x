#!/usr/bin/env bash

mkdir -p ../modularjars

jar --create --file ../modularjars/shared.data.provider@2.0.jar \
--module-version=2.0 --main-class=com.kodcu.shared.data.provider.main.Starter \
-C ../mods/shared.data.provider .