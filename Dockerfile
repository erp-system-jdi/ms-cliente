FROM eclipse-temurin:17-alpine

COPY target/*.jar app.jar

EXPOSE 8080

CMD [ "java", "-jar","-Dspring.profiles.active=local", "/app.jar" ]