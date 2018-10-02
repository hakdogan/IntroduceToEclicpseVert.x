#!/usr/bin/env bash

mvn clean install
java -jar target/workerVerticleLauncher.jar -worker