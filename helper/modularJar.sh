#!/usr/bin/env bash

mkdir -p ../modularjars

jar --create --file ../modularjars/helper@2.0.jar \
--module-version=2.0 \
-C ../mods/helper .