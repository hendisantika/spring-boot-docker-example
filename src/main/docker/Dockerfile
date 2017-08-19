#!/usr/bin/env bash
FROM openjdk:8-jre-alpine

VOLUME /tmp
COPY maven/${project.build.finalName}.${project.packaging} /app.jar
RUN sh -x -c 'touch /app.jar'
RUN sh -x -c 'chmod -v +x /app.jar'

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]