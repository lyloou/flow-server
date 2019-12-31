#!/usr/bin/env bash
NAME=flow-0.0.1.jar

cd $(dirname $0)
ps -ef|grep $NAME|grep -v grep|cut -c 9-15|xargs kill -15
sleep 1
nohup java -Duser.timezone=GMT+08 -Xmx256M -Xms256M -jar $NAME > /dev/null &