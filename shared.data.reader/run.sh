#!/usr/bin/env bash

java -p ../modularjars:../entity/modules:../helper/modules \
 --add-modules java.se \
 --add-exports java.base/jdk.internal.ref=com.hazelcast.core \
 --add-opens java.base/java.lang=com.hazelcast.core \
 --add-opens java.base/java.nio=com.hazelcast.core \
 --add-opens java.base/sun.nio.ch=com.hazelcast.core \
 --add-opens java.management/sun.management=com.hazelcast.core \
 --add-opens jdk.management/com.sun.management.internal=com.hazelcast.core \
-m shared.data.reader