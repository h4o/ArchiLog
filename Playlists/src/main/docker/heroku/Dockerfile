FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
EXPOSE 8080
ENV ARTIFACTID Playlists
ENV VERSION 1.0-SNAPSHOT
ADD tmp/$ARTIFACTID-$VERSION.jar app.jar
RUN sh -c 'touch /app.jar'
CMD java -Dserver.port=$PORT -Dspring.data.mongodb.uri=mongodb://mongouser:password@ds155747.mlab.com:55747/al-db -Djava.security.egd=file:/dev/./urandom  -Xmx32m -Xss256k -jar /app.jar