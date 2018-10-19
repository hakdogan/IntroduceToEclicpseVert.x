#!/usr/bin/env bash

mvn clean install
java -jar target/asyncPutVerticle.jar -cluster
