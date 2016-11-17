FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ENV ARTIFACTID Synchro
ENV VERSION 1.0-SNAPSHOT
ADD tmp/$ARTIFACTID-$VERSION.jar app.j
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://db/playlists","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]