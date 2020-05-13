#!/usr/bin/env bash

mkdir -p ../modularjars

jar --create --file ../modularjars/util@2.0.jar \
--module-version=2.0 \
-C ../mods/util .