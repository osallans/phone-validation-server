FROM openjdk:17-oracle
ADD sample.db sample.db
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/phone-validation-server-0.0.1-SNAPSHOT.jar phone-validation-server.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar phone-validation-server.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar jumiaservice.jar
