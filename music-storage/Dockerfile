FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /data
ADD target/music-storage-1.0-SNAPSHOT-fat.jar app.jar
ADD launch.sh /launch.sh
ADD www/upload.html /www/upload.html
RUN sh -c 'touch /app.jar'
RUN sh -c 'touch /www/upload.html'
EXPOSE 8080
ENV PORT 8080
CMD sh launch.sh
