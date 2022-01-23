# Build apps using Maven
FROM maven:3.8-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn install

# Inject JAR 
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]