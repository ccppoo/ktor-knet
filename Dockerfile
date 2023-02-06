FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 8010:8010
EXPOSE 8020:8020
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/ktor-docker-sample.jar
# temp
COPY ./keystore.jks /app/keystore.jks
COPY ./keystore.jks /keystore.jks
ENTRYPOINT ["java","-jar","/app/ktor-docker-sample.jar"]

#
# # App Building phase --------
# FROM openjdk:11 AS build
#
# RUN mkdir /appbuild
# COPY . /appbuild
#
# WORKDIR /appbuild
#
# RUN ./gradlew clean build
# # End App Building phase --------
#
# # Container setup --------
# FROM openjdk:11
#
# # Creating user
# ENV APPLICATION_USER ktor_user
# RUN useradd $APPLICATION_USER
#
# # Giving permissions
# RUN mkdir /app
# RUN mkdir /app/resources
# RUN chown -R $APPLICATION_USER /app
# RUN chmod -R 755 /app
#
# # Setting user to use when running the image
# USER $APPLICATION_USER
#
# # Copying needed files
# COPY --from=build /appbuild/build/libs/KtorEasy*all.jar /app/KtorEasy.jar
# # COPY --from=build /appbuild/resources/ /app/resources/
# WORKDIR /app
#
# # Entrypoint definition
# # -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:InitialRAMFraction=2 -XX:MinRAMFraction=2 -XX:MaxRAMFraction=2 -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+UseStringDeduplication
# CMD ["sh", "-c", "java -jar KtorEasy.jar"]
# # End Container setup --------