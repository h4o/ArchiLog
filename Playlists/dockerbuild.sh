#!/bin/bash
cp target/Playlists-1.0-SNAPSHOT.jar src/main/docker/tmp
docker build -t playlists src/main/docker