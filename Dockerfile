ARG BUILD_HOME=/workspace

FROM gradle:jdk21 AS build-image

ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME

COPY --chown=gradle:gradle src $APP_HOME/src
COPY --chown=gradle:gradle build.gradle $APP_HOME/build.gradle
COPY --chown=gradle:gradle settings.gradle $APP_HOME/settings.gradle
COPY --chown=gradle:gradle gradle.properties $APP_HOME/gradle.properties
COPY --chown=gradle:gradle gradle $APP_HOME/gradle

RUN ls -alrt src
RUN gradle --no-daemon assemble

FROM eclipse-temurin:21

ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
COPY --from=build-image $APP_HOME/build/libs/forum-0.1-all.jar app.jar

ENTRYPOINT java -jar app.jar -Dmicronaut.environments=$MICRONAUT_ENVIRONMENTS
