#!/usr/bin/env bash

#make sure the compile and modularJar sh files are executed before running this script

java -p ../modularjars:../entity/modules:../helper/modules:modules -m routing