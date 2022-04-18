#!/bin/sh
echo"================================================================="
echo"================ STARTING LOGIN SERVICE             .. =========="
echo"================================================================="
java -Dspring.cloud.config.uri=${CONFIG_CLOUD_SERVER_URI} -Dspring.profiles.active=${PROFILE} \
-jar /usr/local/login-service/login-service.jar