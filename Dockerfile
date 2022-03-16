#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS builder
#WORKDIR /app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


#
# Package stage
#
FROM openjdk:17-oracle
ADD sample.db sample.db
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/phone-validation-server-0.0.1-SNAPSHOT.jar phone-validation-server.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar phone-validation-server.jar
