FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ENV ARTIFACTID eureka-service
ENV VERSION 0.0.1-SNAPSHOT
ADD tmp/$ARTIFACTID-$VERSION.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://db/playlists","-Djava.security.egd=file:/dev/./urandom","-Xmx32m","-Xss256k","-jar","/app.jar"]
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx32m","-Xss256k","-jar","/app.jar"]
