#!/bin/bash

mvn clean install
mvn dependency:copy-dependencies

TD="./target/dependency"

java -cp \
./cdi-api-1.0.jar:\
./cdi-api-1.1.jar:\
$TD/jboss-logging-3.1.3.GA.jar:\
$TD/guava-13.0.1.jar:\
$TD/weld-api-2.2.SP3.jar:\
$TD/javax.inject-1.jar:\
$TD/weld-core-impl-2.2.5-SNAPSHOT.jar:\
$TD/jboss-annotations-api_1.2_spec-1.0.0.Alpha1.jar:\
$TD/weld-environment-common-2.2.5-SNAPSHOT.jar:\
$TD/jboss-classfilewriter-1.0.4.Final.jar:\
$TD/weld-se-core-2.2.5-SNAPSHOT.jar:\
$TD/jboss-el-api_3.0_spec-1.0.0.Alpha1.jar:\
$TD/weld-spi-2.2.SP3.jar:\
$TD/jboss-interceptors-api_1.2_spec-1.0.0.Alpha3.jar:\
./target/weld-se-numberguess-2.2.5-SNAPSHOT.jar org.jboss.weld.environment.se.StartMain
