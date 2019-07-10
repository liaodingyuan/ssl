#!/bin/bash
JAVA_VERSION=`java -version 2>&1 |awk 'NR==1{ gsub(/"/,""); print $3 }'`
if  [[ $JAVA_VERSION != 1.8* ]]  ;then
 echo '当前程序只支持jdk1.8,或者指定jdk 1.8路径(java_cmd)';
fi
java_cmd=java

export HOME=$(cd `dirname $0`/../; pwd)

deploy_file_name=${run.app}.jar

nohup $java_cmd  \
          -Xms2G \
          -Xmx4G \
          -XX:MaxNewSize=1G \
          -XX:+UseConcMarkSweepGC \
          -XX:+HeapDumpOnOutOfMemoryError  -jar \
          $HOME/apps/$deploy_file_name   > /dev/null 2>&1 &

echo Start Success!