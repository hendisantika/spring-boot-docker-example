# Example Spring Boot With Docker

Adapted from [the Spring Boot with Docker guide](https://spring.io/guides/gs/spring-boot-docker/) to use [the Docker Maven Plugin from fabric8.io](https://github.com/fabric8io/docker-maven-plugin). This project demonstrates how to Dockerize a Spring Boot application.

###Getting Started

All Maven plugins and dependencies are available from [Maven Central](https://search.maven.org/). Please have installed

* JDK 1.8 (tested with both Oracle and OpenJDK)
* Maven 3.3+ (also tested with 3.5.x)
* Docker 1.13 or better (17.05.0-ce-rc2 or better for multi-stage builds)


### Building

`mvn clean install`

There are three build profiles that will automatically enable the docker-maven-plugin. These are activated by one of

* `/var/run/docker.sock` existing on the filesystem
* a `$DOCKER_HOST environment` variable in the current shell
* specifying `${docker.host}` on the Maven CLI, e.g.
 `mvn clean install --define docker.host=tcp://localhost:2375`

### Running

### Maven Style

The fabric8io/docker-maven-plugin can be used to run the built container:

`mvn docker:run --define example.port:8080`

Leave off the example.port property definition to let the plugin runtime automatically determine an ephemeral port to utilize.

#### Docker Style

Determine the container name. When built by Maven it is tagged with the `${project.version}`:

```
mvn --quiet exec:exec \
    --define 'exec.executable=echo' \
    --define 'exec.args=dweomer/${project.artifactId}:${project.version}'
```

#### Run via Docker CLI directly:

```
docker run -itd --name example-spring-boot --publish 8080:8080 dweomer/example-spring-boot:1.5-SNAPSHOT
```

#### Test the Endpoint

```
# curl -v http://localhost:8080
* Rebuilt URL to: http://localhost:8080/
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET / HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.47.0
> Accept: */*
>
< HTTP/1.1 200
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 18
< Date: Wed, 03 May 2017 05:35:11 GMT
<
* Connection #0 to host localhost left intact
Hello Docker World
```

#### Docker Multi-Stage Build

The `Dockerfile` at the top of the repository is meant to represent a trivial build pipeline (as code). If you are running Docker 17.05 or better with `--experimental=true` you may execute a single command to invoke the short pipeline.

```
docker build --pull --tag dweomer/example-spring-boot:1.5-SNAPSHOT .
```

Dockerfile-as-Build-Pipeline FTW.

#### Links

* [https://github.com/moby/moby/pull/31257]()
* [https://github.com/moby/moby/pull/32063]()
* [https://medium.com/travis-on-docker/multi-stage-docker-builds-for-creating-tiny-go-images-e0e1867efe5a]()
* [https://htmlpreview.github.io/?https://github.com/DockerOttawaMeetup/Slides/blob/master/2017-04-26-Multi-Stage-Builds/index.html]()
* [http://blog.alexellis.io/mutli-stage-docker-builds/]()

Copyright © 2017 Hendi Santika

>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

>	`http://www.apache.org/licenses/LICENSE-2.0`
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.