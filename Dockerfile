# Use an OpenJDK 17 image as the base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built Spring Boot jar file into the container
COPY target/patient-record-service.jar patient-record-service.jar

# Expose port 8080
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "patient-record-service.jar"]