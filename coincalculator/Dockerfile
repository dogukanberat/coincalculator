FROM openjdk:8-jdk-slim

ADD target/*.jar ./srv
WORKDIR /srv
EXPOSE 8100
ENV JAVA_OPTS=""

CMD java $JAVA_OPTS -jar /srv/*.jar
