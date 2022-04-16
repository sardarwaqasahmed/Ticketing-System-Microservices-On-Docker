FROM openjdk:8-jdk-alpine
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    WAIT_TIME=0 \
    JAVA_OPTS=""
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /config-server/configserver.jar
ENTRYPOINT ["java","-jar","/config-server/configserver.jar"]
EXPOSE 1111