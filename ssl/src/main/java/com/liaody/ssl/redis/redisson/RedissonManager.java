package com.liaody.ssl.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;

/**
 * Redisson客户端管理器
 */
public class RedissonManager {

    private static final String RAtomicName = "genId_";
    private static RedissonClient redissonClient = null;
    private static Config config = new Config();

    /**
     * 初始化Redisson客户端
     */
    public static void init(){
        config
                .setCodec(new org.redisson.client.codec.StringCodec())
                //  .setTransportMode(TransportMode.EPOLL)
                .useMasterSlaveServers()
                // Redission Client会自动识别主从变换 automatic master server change discovery
                // 设置主节点
                .setMasterAddress("redis://192.168.147.140:6379")
                // 设置从节点
                .addSlaveAddress("redis://192.168.147.143:6379", "redis://192.168.147.146:6379")
                // 设置从节点为只读模式
                .setReadMode(ReadMode.SLAVE)
                // 设置负载均衡算法
                .setLoadBalancer(new RoundRobinLoadBalancer());
        redissonClient = Redisson.create(config);
        //清空自增的ID数字
        RAtomicLong atomicLong = redissonClient.getAtomicLong(RAtomicName);
        atomicLong.set(1);
    }

    /**
     * 获取RedissonClient
     * @return RedissonClient
     */
    public static RedissonClient getRedissonClient(){
        if(redissonClient==null){
            init();
        }
        return redissonClient;
    }

    /**
     * 获取id
     * @return id
     */
    public static Long nextID(){
        RAtomicLong atomicLong = getRedissonClient().getAtomicLong(RAtomicName);
        atomicLong.incrementAndGet();
        return atomicLong.get();
    }
}
