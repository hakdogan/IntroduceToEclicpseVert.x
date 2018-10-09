#!/usr/bin/env bash

mvn clean install
java -jar target/clusteredSenderLauncher.jar -cluster