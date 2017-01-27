FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /data
ADD target/music-storage-1.0-SNAPSHOT-fat.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8080
ENV PORT 8080
CMD java -jar /app.jar