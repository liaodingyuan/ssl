package com.liaody.ssl.zookeeper;

import com.alibaba.fastjson.JSON;
import com.liaody.ssl.constants.SslConstants;
import com.liaody.ssl.redis.jedis.RedisUtil;
import com.liaody.ssl.util.MyZkSerializer;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.liaody.ssl.constants.SslConstants.ZK_ROOT_PATH;


/**
 * 使用zkClient监听zk
 *
 * @param <T>
 * @author yuanhaha
 */
@Component
@Slf4j
public class ZkClientWatcher<T> {

    private ZkClient zkClient;
    private static Set<String> serverNodes;
    private RedisUtil redisUtil;

    @PostConstruct
    public void init() throws IOException {

        this.zkClient = new ZkClient(SslConstants.ZK_CONNECT_SERVER_LIST, 20000, 30000, new MyZkSerializer());
        if (!zkClient.exists(ZK_ROOT_PATH)) {
            zkClient.createPersistent(ZK_ROOT_PATH);
        }
        // 监听节点
        lister(ZK_ROOT_PATH);
        serverNodes = new HashSet<>(16);
        List<String> children = zkClient.getChildren(ZK_ROOT_PATH);
        serverNodes.addAll(children);
    }

    private void lister(String path) {
        //对父节点添加监听变化。
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) {
                System.out.printf("变更的节点为:%s,%s", dataPath, data);
            }
            @Override
            public void handleDataDeleted(String dataPath) {
                System.out.printf("删除的节点为:%s", dataPath);
            }
        });

        //对父节点 添加  监听 子节点变化。
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) {

                for(String node:currentChilds){
                  if(!serverNodes.contains(node)) {
                      log.info("节点{}上线", node);
                  }
                }
                serverNodes.removeAll(currentChilds);
                for(String deleteNode:serverNodes){
                    log.info("节点{}下线",deleteNode);
                }
                serverNodes.clear();
                serverNodes.addAll(currentChilds);
                log.info("当前可用节点:{}",serverNodes);
                // 将服务器可用信息缓存到redis
                redisUtil.set("app:servers", JSON.toJSONString(serverNodes),0);
            }
        });
        //对父节点添加 监听 子节点变化。
        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
                if (state == Watcher.Event.KeeperState.SyncConnected) {
                    //当我重新启动后start，监听触发
                    System.out.println("连接成功");
                } else if (state == Watcher.Event.KeeperState.Disconnected) {
                    System.out.println("连接断开");

                } else {
                    //当我在服务端将zk服务stop时，监听触发
                    System.out.println("其他状态" + state);
                }
            }

            @Override
            public void handleNewSession() throws Exception {
                System.out.println("重建session");
            }

            @Override
            public void handleSessionEstablishmentError(Throwable error) throws Exception {
                System.out.println("session连接出现异常");
            }
        });
    }
}
