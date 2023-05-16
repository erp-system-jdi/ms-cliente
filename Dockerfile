FROM public.ecr.aws/amazoncorretto/amazoncorretto:17

COPY *.jar app.jar

EXPOSE ${CUSTOMER_PORT}

CMD [ "java", "-jar","-Dspring.profiles.active=${ENV_PIPE}", "/app.jar" ]
