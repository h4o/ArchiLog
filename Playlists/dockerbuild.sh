#!/bin/bash
cp target/Playlists-1.0-SNAPSHOT.jar src/main/docker/tmp
cp target/Playlists-1.0-SNAPSHOT.jar src/main/docker/heroku/tmp
#docker build -t playlists src/main/docker