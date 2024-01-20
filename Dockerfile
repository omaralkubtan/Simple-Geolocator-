FROM zisa/maven-3.8.1-openjdk-17-armv7 as build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Stage 2: Run the application
FROM ubuntu/jre
COPY --from=build /home/app/target/geolocator-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]