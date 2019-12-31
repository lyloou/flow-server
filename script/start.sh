#!/bin/bash
NAME=flow-0.0.1.jar

nohup java -Duser.timezone=GMT+08 -Xmx256M -Xms256M -jar ../release/$NAME > /dev/null &