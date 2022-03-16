#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS builder
WORKDIR /app
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app
COPY sample.db /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package


#
# Package stage
#
FROM openjdk:17-oracle
WORKDIR /app
ADD sample.db sample.db
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY --from=builder /usr/src/app/target/phone-validation-server-0.0.1-SNAPSHOT.jar phone-validation-server.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar phone-validation-server.jar