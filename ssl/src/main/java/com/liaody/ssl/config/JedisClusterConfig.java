package com.liaody.ssl.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class JedisClusterConfig {

    @Bean
    public JedisCluster jedisCluster() {
        // 初始化所有节点（例如6个节点）
        Set<HostAndPort> jedisClusterNode = new HashSet<>(8);
        jedisClusterNode.add(new HostAndPort("192.168.147.133", 7001));
        jedisClusterNode.add(new HostAndPort("192.168.147.133", 7002));
        jedisClusterNode.add(new HostAndPort("192.168.147.133", 7003));
        jedisClusterNode.add(new HostAndPort("192.168.147.133", 7004));
        jedisClusterNode.add(new HostAndPort("192.168.147.133", 7005));
        jedisClusterNode.add(new HostAndPort("192.168.147.133", 7006));
        // 通用化配置
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        return new JedisCluster(jedisClusterNode, 1000, 5, config);
    }
}
