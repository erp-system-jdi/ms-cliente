FROM public.ecr.aws/amazoncorretto/amazoncorretto:17

COPY target/*.jar app.jar

#EXPOSE ${CUSTOMER_PORT}
EXPOSE 8080

CMD [ "java", "-jar","-Dspring.profiles.active=local", "/app.jar" ]
