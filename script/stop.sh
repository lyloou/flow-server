#!/bin/sh
NAME=flow-0.0.1.jar

cd $(dirname $0)
ps -ef|grep $NAME|grep -v grep|cut -c 9-15|xargs kill -15