# Flow 服务端

## 部署
#### mysql
- 数据库结构`src/main/resources/sql`
- 配置： `src/main/resources/application-dev.properties#spring.datasource`

#### redis 
- 配置： `src/main/resources/application-dev.properties#redis`

## 一键编译和运行
```shell script
alias xflow='cd $HOME/w/java/flow-server/ && git pull && mvn package && cd script && ./restart.sh && cd ..'
```
