FROM public.ecr.aws/docker/library/eclipse-temurin:17-jre-alpine

ENV JAVA_OPTS="-Xmx512m -Xms256m -Djava.security.egd=file:/dev/./urandom" \
    SPRING_PROFILES_ACTIVE=prod

RUN apk add --no-cache curl=8.14.1-r2 tzdata=2025b-r0

USER nobody:nogroup
WORKDIR /app

COPY --chown=nobody:nogroup target/*.jar /app/app.jar

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]