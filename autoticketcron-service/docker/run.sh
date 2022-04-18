#!/bin/sh
echo"================================================================="
echo"================ STARTING AUTOTICKETCRON SERVICE             .. =========="
echo"================================================================="
java $JAVA_OPT -Dspring.cloud.config.uri=${CONFIG_CLOUD_SERVER_URI} -Dspring.profiles.active=${PROFILE} \
-jar /usr/local/autoticketcron-service/autoticketcron-service.jar