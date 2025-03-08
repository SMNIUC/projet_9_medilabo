# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Add the built JAR file into the container
ADD target/medilabo-doc-api-1.0-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application runs on (default: 8080)
EXPOSE 8082

# Define the entrypoing to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]