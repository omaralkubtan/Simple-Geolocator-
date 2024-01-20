# Start with a base image containing Java runtime (OpenJDK 20)
FROM openjdk:20-jdk-alpine

# Add Maintainer Info
LABEL maintainer="omarquptan@gmail.com"

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/geolocator-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} geolocator.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","/geolocator.jar"]