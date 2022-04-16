FROM openjdk:8-jdk-alpine
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    WAIT_TIME=0 \
    JAVA_OPTS=""
VOLUME /orderdelivery-service
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /orderdelivery-service/orderdelivery.jar
ENTRYPOINT ["java","-jar","/orderdelivery-service/orderdelivery.jar"]
EXPOSE 9090