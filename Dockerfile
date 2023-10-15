FROM public.ecr.aws/amazoncorretto/amazoncorretto:17

COPY *.jar app.jar

EXPOSE 8080

CMD [ "java", "-jar","-Dspring.profiles.active=${ENVIROMENT}", "/app.jar" ]
