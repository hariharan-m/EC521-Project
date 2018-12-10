#!/bin/sh
if [ -d /docker-java-home/jre ]; then
  export JAVA_HOME=/docker-java-home/jre
  export JAVA_OPTS=""
fi
