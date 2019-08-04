package com.liaody.ssl.zookeeper;

import com.liaody.ssl.constants.SslConstants;
import com.liaody.ssl.util.MyZkSerializer;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.liaody.ssl.constants.SslConstants.ZK_ROOT_PATH;

/**
 * 使用zkClient监听zk
 * @author yuanhaha
 * @param <T>
 */
@Component
@Slf4j
public class ZkClientWatcher<T> {

    ZkClient zkClient;
    public ZkClientWatcher() {
        this.zkClient = new ZkClient(SslConstants.ZK_CONNECT_SERVER_LIST, 5000, 5000, new MyZkSerializer());
    }

    public T readData(String path) {
        return zkClient.readData(path);

    }

    public List<String> getChildren(String path) {
        return zkClient.getChildren(path);

    }

    public void writeData(String path, Object object) {
        zkClient.writeData(path, object);

    }

    public void deleteRecursive(String path) {
        zkClient.deleteRecursive(path);

    }

    /***
     *
     * @param path
     * @param data
     */
    public void createPersistent(String path, Object data) {
        zkClient.createPersistent(path, data);
    }


    public void lister(String path) {
        path = ZK_ROOT_PATH+"/"+path;

        //对父节点添加监听变化。
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.printf("变更的节点为:%s,%s", dataPath, data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.printf("删除的节点为:%s", dataPath);
            }
        });
        //对父节点 添加  监听 子节点变化。
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("parentPath: " + parentPath + ",currentChilds:" + currentChilds);
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
                } else{
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
            }
        });

    }
}