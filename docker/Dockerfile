FROM openjdk:24

WORKDIR /app

COPY target/spring-feature-service-3.5.0-SNAPSHOT.jar /app/feature-service.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/feature-service.jar"]