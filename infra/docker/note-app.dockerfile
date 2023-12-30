FROM openjdk:17-jdk-slim-buster

WORKDIR /app

COPY ./build/

ENTRYPOINT java -jar app.jar
