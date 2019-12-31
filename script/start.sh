#!/bin/sh
NAME=flow-0.0.1.jar

cd $(dirname $0)
nohup java -Duser.timezone=GMT+08 -Xmx256M -Xms256M -jar $NAME > /dev/null &