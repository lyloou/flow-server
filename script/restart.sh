#!/bin/sh
NAME=flow-0.0.1.jar
ps -ef|grep $NAME|grep -v grep|cut -c 9-15|xargs kill -15
sleep 1
nohup java -Duser.timezone=GMT+08 -Xmx256M -Xms256M -jar ../release/$NAME > /dev/null &