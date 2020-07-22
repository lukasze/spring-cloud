#!/bin/bash
while ! nc -z config-server 8888 ; do
    echo "Waiting for the Config Server"
    sleep 10
done
echo "Config Server isrunning - starting the Eureka Server"

java -cp /app/resources:/app/classes:/app/libs/* workshop.sc.eureka.EurekaApplication