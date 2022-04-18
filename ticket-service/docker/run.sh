#!/bin/sh
echo"================================================================="
echo"================ STARTING TICKET SERVICE             .. =========="
echo"================================================================="
java -Dspring.cloud.config.uri=${CONFIG_CLOUD_SERVER_URI} -Dspring.profiles.active=${PROFILE} \
-jar /usr/local/ticket-service/ticket-service.jar