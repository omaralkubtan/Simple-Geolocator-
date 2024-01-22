FROM maven:3.8.7-openjdk-18-slim as maven_build
COPY pom.xml pom.xml

COPY src src
RUN mvn compile
# The application's jar file
ARG JAR_FILE=target/geolocator-0.0.1-SNAPSHOT.jar

FROM openjdk:20

COPY --from=maven_build $JAR_FILE /geolocator.jar

RUN chmod +x /geolocator.jar

# Add Maintainer Info
LABEL maintainer="omarquptan@gmail.com"

# Make port 9000 available to the world outside this container
EXPOSE 9000

# Set environment variables
ENV DB_HOST=host.docker.internal
ENV DATABASE_URL;
ENV DATABASE_USERNAME;
ENV DATABASE_PASSWORD;

# Run the jar file
ENTRYPOINT ["java","-jar","/geolocator.jar"]
