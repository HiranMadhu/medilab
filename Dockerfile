FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/notification-service.jar notification-service.jar
EXPOSE 8080
CMD ["java", "-jar", "notification-service.jar"]
