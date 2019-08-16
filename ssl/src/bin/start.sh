#!/bin/bash
JAVA_VERSION=`java -version 2>&1 |awk 'NR==1{ gsub(/"/,""); print $3 }'`
if  [[ $JAVA_VERSION != 1.8* ]]  ;then
 echo '当前程序只支持jdk1.8,或者指定jdk 1.8路径(java_cmd)';
fi
java_cmd=java

export HOME=$(cd `dirname $0`/../; pwd)

deploy_file_name=${run.app}.jar
# -Xms JVM初始分配的对内存
# -Xmx JVM最大允许分配的堆内存
# -XX:PermSize=64M JVM初始分配的非堆内存, 不会被回收, 生产环境建议与maxPermSize相同, 设为256m以上
# -XX:MaxPermSize=128M JVM最大允许分配的非堆内存，按需分配。生产环境建议设置为256m以上
# -Xss是对每个线程stack大小的调整。直接影响对方法的调用次数
# -XX:MaxNewSize=512m JVM堆区域新生代内存的最大可分配大小(PermSize不属于堆区), 生产环境建议设为800M-1024M
# +UseConcMarkSweepGC 使用CMS内存收集：CMS全称 Concurrent Mark Sweep，是一款并发的、使用标记-清除算法的垃圾回收器，
#如果老年代使用CMS垃圾回收器，需要添加虚拟机参数-"XX:+UseConcMarkSweepGC"。使用场景：GC过程短暂停，适合对时延要求较高的服务，用户线程不允许长时间的停顿。

nohup $java_cmd  \
          -Xms2G \
          -Xmx4G \
          -XX:MaxNewSize=1G \
          -XX:+UseConcMarkSweepGC \
          -XX:+HeapDumpOnOutOfMemoryError  -jar \
          $HOME/apps/$deploy_file_name   > /dev/null 2>&1 &

echo Start Success!