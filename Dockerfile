FROM public.ecr.aws/amazoncorretto/amazoncorretto:17-arm64

COPY target/*.jar app.jar

ARG ENVIROMENT

EXPOSE 8080

CMD [ "java", "-jar", "/app.jar" ]
