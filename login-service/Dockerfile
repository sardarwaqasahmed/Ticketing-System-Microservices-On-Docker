FROM openjdk:8-jdk-alpine
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    WAIT_TIME=0 \
    JAVA_OPTS=""
VOLUME /login-service
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /login-service/login.jar
ENTRYPOINT ["java","-jar","/login-service/login.jar"]
EXPOSE 9091