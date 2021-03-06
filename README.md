master<br>
[![Build Status](https://travis-ci.org/abedurftig/tdb-service.svg?branch=master)](https://travis-ci.org/abedurftig/tdb-service)

develop<br>
[![Build Status](https://travis-ci.org/abedurftig/tdb-service.svg?branch=develop)](https://travis-ci.org/abedurftig/tdb-service)

### Test Dashboard Service

The _Test Dashboard Service_ is a `Spring Boot` application which let's you upload the test results of your projects. Currently only the Junit 4 XML files are supported.
The results will be stored and the service then provides a REST API documented with `Swagger` to read the data again.

The [Test Dashboard](https://tdb-app.herokuapp.com) is a client. It retrieves the test run data for your projects and displays diagrams visualizing your tests. Checkout the repository also here at [GitHub](https://github.com/abedurftig/tdb-app).  

To checkout the current API head over to the [Swagger UI](https://tdb-service.herokuapp.com/api/swagger-ui.html).

#### Installation

This project uses Gradle as a build tool.

```
./gradlew build && java -jar build/libs/tdb-service.jar
```
This will start the application with an H2 memory DB. Currently the only other database system supported is Postgres. To start against a Postgres DB, you need to set the a environment variable `DATABASE_URL`, for example:

```
postgres://user:pw@host:port/db
``` 
Then run start the server like this:

```
java -jar build/libs/tdb-service.jar --spring.profiles.active=pg-standalone
```

#### Deployment

This application is currently deployed to Heroku. For this deployment from Travis the `heroku/jvm` buildpack is used. Have a look at the `Procfile`.

#### Docker

You can create a Dockerfile with Gradle.

```
./gradlew createDockerfile
```

Build the image.
```
cp build/libs/tdb-service.jar build/docker/jdk8/
docker build -f build/docker/jdk8/Dockerfile -t tdb-service:latest .
```

Then start a container. In the example below it will provide the Postgres DB on the docker host.

```
# Docker for Mac
docker run -e DATABASE_URL='postgres://tdb:tdb@docker.for.mac.host.internal:5432/tdb' \
-e SPRING_PROFILES_ACTIVE='pg-standalone' \
-p 80:8080 -t tdb-service:latest
```

#### Thanks

- Spring Boot
- Gradle
- Swagger
- Travis
- Heroku