package com.liaody.ssl.zookeeper;

import com.liaody.ssl.constants.SslConstants;
import com.liaody.ssl.util.MyZkSerializer;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.liaody.ssl.constants.SslConstants.ZK_ROOT_PATH;

/**
 * zkClient增删改查
 *
 * @author yuanhaha
 */
//@Component
@Slf4j
public class ZkClientCrud<T> {


    /**
     * zkClient
     */
    private ZkClient zkClient;

    /**
     * 初始化
     */
//    @PostConstruct
    public void init() {
        // 别忘了定义序列化方式
        zkClient = new ZkClient(SslConstants.ZK_CONNECT_SERVER_LIST, 5000, 5000, new MyZkSerializer());
    }

    /**
     * 创建有序节点
     * @param path 路径
     * @param data 节点的数据，不能太大
     */
    public void createEphemeral(String path, Object data) {
        zkClient.createEphemeralSequential(ZK_ROOT_PATH+"/"+path, data);
    }

    /**
     * 创建 永久 节点
     * @param path 路径
     * @param data 节点的数据，不能太大
     */
    public void createPersistent(String path, Object data) {
        zkClient.createPersistent(path, data);
    }

    /**
     * 创建alc节点
     * @param path 路径
     * @param data 节点的数据，不能太大
     */
    public void createAcl(String path, Object data) {
        zkClient.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    /**
     * 读取数据
     * @param path 路径
     * @return 数据
     */
    public T readData(String path) {
        //没有不会抛异常，而是返回null
        return zkClient.readData(path, true);
    }

    /**
     * 读取 子节点 只能找 其 子一级 下 所有的
     */
    public List<String> getChildren(String path) {
        return zkClient.getChildren(path);
    }

    /**
     * 递归查找 所有 子节点
     */
    public void getChilderRecursive(String path) {
        System.out.println(path);
        if (zkClient.exists(path)) {
            List<String> list = zkClient.getChildren(path);
            if (list.size() > 0) {
                list.stream().forEach(n -> {
                    getChilderRecursive(path + "/" + n);
                });
            }
        }

    }


    // 更新内容
    public void writeData(String path, Object object) {
        zkClient.writeData(path, object);

    }

    //删除单个节点 即这个节点下不能有子节点
    public void delete(String path) {
        zkClient.delete(path);
    }


    //递归删除节点 即删除其节点下 所有子节点  对应rmr 命令
    public void deleteRecursive(String path) {
        zkClient.deleteRecursive(path);
    }


    /***
     * 支持创建递归方式 但是不能写入数据
     * @param path
     * @param createParents   boolean
     */
    public void createPersistentRecursive(String path, boolean createParents) {
        zkClient.createPersistent(path, createParents);

        zkClient.create(path, "123", ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

    }

    /**
     * 关闭zk客户端
     */
    public void close(){

        if(zkClient !=null){
            try {
                zkClient.close();
            } finally {
                zkClient = null;
                System.gc();
            }
        }
    }
}
