#!/bin/sh
echo "****************************************************************"
echo "		SPRING CLOUD CONFIG SERVER STARTING On Docker             "
echo "****************************************************************"
java $JAVA_OPT -Dname.prefix=$NAME_PREFIX \
-jar /usr/local/config-server/config-server.jar