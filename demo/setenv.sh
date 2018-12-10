#!/bin/sh

#export JAVA_HOME=../../jre-inst-int/
export JAVA_HOME=../../jdk1.8-inst
export PATH="$JAVA_HOME:$JAVA_HOME/bin:$PATH"
export JAVA_OPTS="-Xbootclasspath/a:../../Phosphor-0.0.4-SNAPSHOT.jar -javaagent:../../Phosphor-0.0.4-SNAPSHOT.jar"
