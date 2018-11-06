FROM openjdk:8-jdk-alpine
MAINTAINER ykbt-orm

VOLUME /tmp

RUN mkdir /app
WORKDIR /app

ENV JAR_TARGET "ykbt-orm-java-1.0-SNAPSHOT.jar"

ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=docker build/libs/${JAR_TARGET}"]
