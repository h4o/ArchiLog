#!/bin/sh
#JesuisUnPassword
#ssh root@46.101.31.80
#-v ./data:/data
nohup docker run -p 27017:27017 --name mongo mongo &
nohup docker run -p 8080:8080 	 --name music-storage --link mongo music-storage &
