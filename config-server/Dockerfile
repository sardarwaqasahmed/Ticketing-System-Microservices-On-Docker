FROM openjdk:8-jdk-alpine
RUN apk add --no-cache --upgrade bash
ARG JAR_FILE=target/*.jar
RUN mkdir -p /usr/local/config-server
ADD ${JAR_FILE} /usr/local/config-server
ADD docker/run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh