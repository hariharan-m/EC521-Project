#!/bin/sh

PATH="jdk1.8.0_191/bin":$PATH

java -jar Phosphor-0.0.4-SNAPSHOT.jar \
    -taintSources taint-sources -taintSinks taint-sinks \
    jdk1.8.0_191 jdk1.8-inst/

