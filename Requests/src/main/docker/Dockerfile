FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ENV ARTIFACTID Requests
ENV VERSION 1.0-SNAPSHOT
ADD tmp/$ARTIFACTID-$VERSION.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://db/requests","-Djava.security.egd=file:/dev/./urandom","-Xmx256m","-Xss256k","-jar","/app.jar"]