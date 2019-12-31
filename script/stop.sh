#!/bin/bash
cd $(dirname $0)
source env.sh

ps -ef|grep $NAME|grep -v grep|cut -c 9-15|xargs kill -15
