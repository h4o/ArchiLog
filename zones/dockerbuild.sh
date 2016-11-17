#!/bin/bash
cp target/zones-1.0-SNAPSHOT.jar src/main/docker/tmp
cp target/zones-1.0-SNAPSHOT.jar src/main/docker/heroku/tmp
#docker build -t zones src/main/docker