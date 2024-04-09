FROM openjdk:17-jdk-alpine
VOLUME /tmp

ARG projectName="spring_cicd_person"
ARG springProfile="default"

ENV JAVA_OPTS=""

RUN mkdir /app

COPY ./${projectName}/build/libs/${projectName}.jar /app

ENTRYPOINT exec java -jar $JAVA_OPTS \
  -Djava.security.egd=file:/dev/./urandom \
  /app/${projectName}.jar \
  --spring.profiles.active=${springProfile}
