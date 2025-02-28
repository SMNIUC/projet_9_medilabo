# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/medilabo-gateway-1.0-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application runs on (default: 8080)
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]