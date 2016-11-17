FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ENV ARTIFACTID Synchro
ENV VERSION 1.0-SNAPSHOT
ADD tmp/$ARTIFACTID-$VERSION.jar app.jar
RUN sh -c 'touch /app.jar'
CMD java -Dserver.port=$PORT -Djava.security.egd=file:/dev/./urandom -Xmx32m -Xss256k -jar /app.jar