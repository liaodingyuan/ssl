package com.liaody.ssl.constants;

/**
 * 常量类，应该可以使用枚举代替，或者使用动态配置中心
 * @author yuanhaha
 */
public class SslConstants {

    /**
     * zk服务器列表
     */
    public static final  String ZK_CONNECT_SERVER_LIST = "192.168.147.140:2181,192.168.147.143:2181,192.168.147.146:2181";
    /**
     * zk项目的根目录
     */
    public static final String ZK_ROOT_PATH = "/app";
    /**
     * kafka服务器列表
     */
    public static final String KAFKA_BOOTSTRAP_SERVER_LIST = "192.168.147.140:9092,192.168.147.143:9092,192.168.147.146:9092";
    /**
     * Redis服务器地址
     */
    public static final String REDIS_SERVER_LIST ="";


}
