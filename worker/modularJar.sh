#!/usr/bin/env bash

#make sure the helper module jar is created before running this script

mkdir -p ../modularjars

jar --create --file ../modularjars/worker@2.0.jar \
--module-version=2.0 \
--main-class=com.kodcu.worker.main.Starter \
-C ../mods/worker .