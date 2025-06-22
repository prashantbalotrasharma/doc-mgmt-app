# Use a lightweight Java runtime base image
FROM openjdk:21-jdk-slim

# Author info (optional)
LABEL authors="Prashant Balotra"

# Set working directory
WORKDIR /app

# Copy the JAR built by Gradle
COPY build/libs/doc-mgmt-app-*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
