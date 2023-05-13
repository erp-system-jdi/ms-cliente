FROM eclipse-temurin:17-alpine

COPY *.jar app.jar

EXPOSE 8080

CMD [ "java", "-jar","-Dspring.profiles.active=dev", "/app.jar" ]
