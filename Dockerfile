FROM public.ecr.aws/docker/library/eclipse-temurin:17-alpine

COPY *.jar app.jar

EXPOSE ${CUSTOMER_PORT}

CMD [ "java", "-jar","-Dspring.profiles.active=dev", "/app.jar" ]
