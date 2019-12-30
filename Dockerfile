FROM gradle:jdk8 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build
VOLUME /tmp
RUN gradle
COPY build/libs/*.jar greenback.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/greenback.jar"]
