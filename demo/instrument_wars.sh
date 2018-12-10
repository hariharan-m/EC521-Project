#!/bin/sh

PATH="jdk1.8.0_191/bin":$PATH
JAVA_HOME=jdk1.8.0_191
TOMCAT_ROOT="apache-tomcat-inst";

java -jar Phosphor-0.0.4-SNAPSHOT.jar \
    -taintSources taint-sources -taintSinks taint-sinks \
    struts-vuln-server.war struts-vuln-server-inst/

java -jar Phosphor-0.0.4-SNAPSHOT.jar \
    -taintSources taint-sources -taintSinks taint-sinks \
    struts2-rest-showcase.war struts-vuln-server-inst/

rm -rf $TOMCAT_ROOT/webapps/struts-*

cp struts-vuln-server-inst/*.war $TOMCAT_ROOT/webapps/