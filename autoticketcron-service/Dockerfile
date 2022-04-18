FROM openjdk:8-jdk-alpine
RUN apk add --no-cache --upgrade bash
ARG JAR_FILE=target/*.jar

RUN mkdir -p /usr/local/autoticketcron-service/

ADD ${JAR_FILE} /usr/local/autoticketcron-service/
ADD docker/run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh