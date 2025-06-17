# Use Java 23 base image
FROM openjdk:23

# Create app directory inside container
WORKDIR /app

# Copy the built jar file into the container
COPY target/project-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 9999

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
