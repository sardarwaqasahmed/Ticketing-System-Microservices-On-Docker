FROM openjdk:8-jdk-alpine
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    WAIT_TIME=0 \
    JAVA_OPTS=""
VOLUME /autoticketcron-service
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /autoticketcron-service/autoticketcron.jar
ENTRYPOINT ["java","-jar","/autoticketcron-service/autoticketcron.jar"]