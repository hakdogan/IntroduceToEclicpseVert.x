#!/usr/bin/env bash

mkdir -p ../modularjars

jar --create --file ../modularjars/helper@1.0.jar \
--module-version=1.0 \
-C ../mods/helper .