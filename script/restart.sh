#!/bin/bash
cd $(dirname $0)
source env.sh

ps -ef|grep $NAME|grep -v grep|cut -c 9-15|xargs kill -15
sleep 1
cd $RELEASE_DIR
nohup java -Duser.timezone=GMT+08 -Xmx256M -Xms256M -jar $NAME > /dev/null &
