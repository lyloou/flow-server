#!/bin/bash
. ./env.sh
ps -ef|grep $NAME|grep -v grep|cut -c 9-15|xargs kill -15
