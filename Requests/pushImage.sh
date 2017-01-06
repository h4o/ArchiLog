#!/bin/bash
mvn clean package
./dockerbuild.sh
cd ./src/main/docker/heroku
heroku container:push --app al-requests