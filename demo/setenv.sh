#!/bin/sh

#export JAVA_HOME=../../jre-inst-int/
if [ -d /phosphor/Phosphor/target/jre-inst-int ]; then
  export JAVA_HOME=/phosphor/Phosphor/target/jre-inst-int
  export PHJ=/phosphor/Phosphor/target/Phosphor-0.0.4-SNAPSHOT.jar
else
  export JAVA_HOME=../../jdk1.8-inst
  export PHJ=../../Phosphor-0.0.4-SNAPSHOT.jar
fi
export PATH="$JAVA_HOME:$JAVA_HOME/bin:$PATH"
export JAVA_OPTS="-Xbootclasspath/a:$PHJ -javaagent:$PHJ"
