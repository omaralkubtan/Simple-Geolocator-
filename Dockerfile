# Example of custom Java runtime using jlink in a multi-stage container build
FROM maven:3.8.1-openjdk-11-slim as build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Define your base image
FROM debian:buster-slim
COPY --from=build /home/app/target/geolocator-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]