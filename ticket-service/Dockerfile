FROM openjdk:8-jdk-alpine
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    WAIT_TIME=0 \
    JAVA_OPTS=""
VOLUME /ticket-service
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /ticket-service/ticket.jar
ENTRYPOINT ["java","-jar","/ticket-service/ticket.jar"]