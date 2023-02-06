# ------ Build Stage ------
FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon


# ---- Container setup ----
FROM openjdk:11

EXPOSE 8010:8010

RUN mkdir /app
RUN mkdir /app/openapi

WORKDIR /app

COPY ./openapi/documentation.yaml /app/openapi/documentation.yaml
COPY --from=build /home/gradle/src/build/libs/*.jar /app/ktor-docker-sample.jar

ENTRYPOINT ["java","-jar","/app/ktor-docker-sample.jar"]
