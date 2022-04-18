#!/bin/sh
echo"================================================================="
echo"================ STARTING ORDER SERVICE             .. =========="
echo"================================================================="
java $JAVA_OPT -Dspring.cloud.config.uri=${CONFIG_CLOUD_SERVER_URI} -Dspring.profiles.active=${PROFILE} \
-jar /usr/local/login-service/login-service.jar