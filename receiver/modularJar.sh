#!/usr/bin/env bash

#make sure the helper module jar is created before running this script

mkdir -p ../modularjars

jar --create --file ../modularjars/receiver@2.0.jar \
--module-version=2.0 \
--main-class=com.kodcu.clustered.receiver.main.Starter \
-C ../mods/receiver .