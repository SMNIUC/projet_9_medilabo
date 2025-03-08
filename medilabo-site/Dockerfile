# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Add the built JAR file into the container
ADD target/medilabo-site-1.0-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application runs on (default: 8080)
EXPOSE 8084

# Define the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]