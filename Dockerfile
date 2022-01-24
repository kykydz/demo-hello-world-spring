# Build apps using Maven
FROM maven:3.8-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn install

# Inject JAR 
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/demo-hello-world.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]