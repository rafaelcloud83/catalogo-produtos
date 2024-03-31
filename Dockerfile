FROM eclipse-temurin:17-jdk-alpine
COPY target/catalogo-produtos-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]