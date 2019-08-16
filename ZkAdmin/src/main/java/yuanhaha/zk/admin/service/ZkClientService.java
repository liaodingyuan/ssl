//package yuanhaha.zk.admin.service;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.I0Itec.zkclient.IZkChildListener;
//import org.I0Itec.zkclient.IZkDataListener;
//import org.I0Itec.zkclient.IZkStateListener;
//import org.I0Itec.zkclient.ZkClient;
//import org.apache.zookeeper.Watcher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import yuanhaha.zk.admin.config.RedisUtil;
//import yuanhaha.zk.admin.util.MyZkSerializer;
//
//import javax.annotation.PostConstruct;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
////@Service
//@Slf4j
//public class ZkClientService {
//
//    private ZkClient zkClient;
//    private static Set<String> serverNodes;
////    @Autowired
//    private RedisUtil redisUtil;
////    @Value("${zk.server.list}")
//    private String zkConnectServerList;
//    @Value("${zk.root}")
//    private String zkRootPath;
//
//    @PostConstruct
//    public void init() {
//        this.zkClient = new ZkClient(zkConnectServerList, 20000, 30000, new MyZkSerializer());
//        if (!zkClient.exists(zkRootPath)) {
//            zkClient.createPersistent(zkRootPath);
//        }
//        // 监听节点
//        lister(zkRootPath);
//        serverNodes = new HashSet<>(16);
//        List<String> children = zkClient.getChildren(zkRootPath);
//        serverNodes.addAll(children);
//    }
//
//    private void lister(String path) {
//        //对父节点添加监听变化。
//        zkClient.subscribeDataChanges(path, new IZkDataListener() {
//            @Override
//            public void handleDataChange(String dataPath, Object data) {
//                System.out.printf("变更的节点为:%s,%s", dataPath, data);
//            }
//
//            @Override
//            public void handleDataDeleted(String dataPath) {
//                System.out.printf("删除的节点为:%s", dataPath);
//            }
//        });
//
//        //对父节点 添加  监听 子节点变化。
//        zkClient.subscribeChildChanges(path, new IZkChildListener() {
//            @Override
//            public void handleChildChange(String parentPath, List<String> currentChilds) {
//
//                for (String node : currentChilds) {
//                    if (!serverNodes.contains(node)) {
//                        log.info("节点{}上线", node);
//                    }
//                }
//                serverNodes.removeAll(currentChilds);
//                for (String deleteNode : serverNodes) {
//                    log.info("节点{}下线", deleteNode);
//                }
//                serverNodes.clear();
//                serverNodes.addAll(currentChilds);
//                log.info("当前可用节点:{}", serverNodes);
//                // 只能单节点运行
//                redisUtil.set("zkRootPath", JSON.toJSONString(serverNodes), 0);
//            }
//        });
//        //对父节点添加 监听 子节点变化。
//        zkClient.subscribeStateChanges(new IZkStateListener() {
//            @Override
//            public void handleStateChanged(Watcher.Event.KeeperState state) {
//                if (state == Watcher.Event.KeeperState.SyncConnected) {
//                    //当我重新启动后start，监听触发
//                    System.out.println("连接成功");
//                } else if (state == Watcher.Event.KeeperState.Disconnected) {
//                    System.out.println("连接断开");
//
//                } else {
//                    //当我在服务端将zk服务stop时，监听触发
//                    System.out.println("其他状态" + state);
//                }
//            }
//
//            @Override
//            public void handleNewSession() {
//                System.out.println("重建session");
//            }
//
//            @Override
//            public void handleSessionEstablishmentError(Throwable error) {
//                System.out.println("session连接出现异常");
//            }
//        });
//    }
//}
