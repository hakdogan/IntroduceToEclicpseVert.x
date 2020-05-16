#!/usr/bin/env bash

#make sure the compile and modularJar sh files are executed before running this script

java --enable-preview -p ../modularjars:../helper/modules:modules -m receiver