#!/bin/bash
# TODO 14
# To make a long story short:
# Zanim uruchomisz aplikację cookie-service,
# upewnij się, że config-server i 2 instancje eureki działają

while ! nc -z config-server 8888 ; do
    echo "Waiting for the Config Server"
    sleep 10
done
while ! nc -z eureka-peer1 8761 ; do
    echo "Waiting for Eureka Peer1"
    sleep 10
done
while ! nc -z eureka-peer2 8762 ; do
    echo "Waiting for Eureka Peer2"
    sleep 10
done
echo "Config and Eureka Servers are running - starting the Microservice"
# Gdy chcemy uruchomić aplikację spring boot, jesteśmy przyzwyczajeni do java -jar
# Poniższa komenda wynika ze sposobu, w jaki jib buduje obraz z aplikacją spring boot
java -cp /app/resources:/app/classes:/app/libs/* workshop.sc.cs.CookieServiceApplication
