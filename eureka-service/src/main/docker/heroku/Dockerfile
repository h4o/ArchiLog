FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ENV ARTIFACTID eureka-service
ENV VERSION 0.0.1-SNAPSHOT
ADD tmp/$ARTIFACTID-$VERSION.jar app.jar
RUN sh -c 'touch /app.jar'
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]