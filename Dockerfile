# BUILD REACTOR
FROM maven:3.5-alpine AS reactor
ARG MAVEN_OPTS="-Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
ENV MAVEN_OPTS="${MAVEN_OPTS}"
ADD . /mvn/src
WORKDIR /mvn/src
RUN set -ex \
 && mvn --batch-mode --show-version \
        --define 'maven.repo.local=/mvn/repo' \
        dependency:list-repositories \
        dependency:go-offline \
        --define 'altDeploymentRepository=local::default::file:///mvn/lib' \
        deploy

# ANALYZE (this doesn't really do anything, but you get the idea)
FROM reactor AS analyze
ARG MAVEN_OPTS="-Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
ENV MAVEN_OPTS="${MAVEN_OPTS}"
RUN set -ex \
 && mvn --quiet exec:exec --define exec.executable=echo --define 'exec.args=${project.groupId}:${project.artifactId}:${project.version}:${project.packaging}'
RUN set -ex \
 && mvn sonar:sonar \
        --define sonar.analysis.mode=preview \
        --define sonar.host.url=http://nemo.sonarsource.org

# APPLICATION CONTAINER
FROM openjdk:8-jre-alpine AS app
VOLUME /tmp
COPY --from=reactor /mvn/src/target/app.jar /
RUN sh -x -c 'touch /app.jar'
RUN sh -x -c 'chmod -v +x /app.jar'

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]