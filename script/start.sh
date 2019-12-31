#!/bin/sh

source ./env.sh
cd $RELEASE_DIR
nohup java -Duser.timezone=GMT+08 -Xmx256M -Xms256M -jar $NAME > /dev/null &