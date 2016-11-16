#!/bin/sh
mvn clean package && cd Playlists && ./dockerbuild.sh && cd ../Synchro && ./dockerbuild.sh && cd .. && docker-compose build --no-cache && docker-compose up