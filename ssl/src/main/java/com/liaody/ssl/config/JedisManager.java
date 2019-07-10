package com.liaody.ssl.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.*;

@Slf4j
public class JedisManager {

//    @Value("${spring.redis.host}")
//    private static String host;
//    @Value("${spring.redis.port}")
//    private static String port;
//    @Value("${spring.redis.jedis.pool.maxActive}")
//    private static String maxActive;
//    @Value("${spring.redis.jedis.pool.maxWait}")
//    private static String maxWait;
//    @Value("${spring.redis.jedis.pool.maxIdle}")

    /**
     * Redis 池
     */
    private static JedisPool pool;

    /**
     * 初始化
     */
    static{
        initJedisPool();
    }

    /**
     * 从连接池种获取一个Jedis实例
     * @return Jedis实例
     */
    public static Jedis getJedisInstance() {
        // 从jedis池中获取一个jedis实例
        return pool.getResource();
    }

    /**
     * 初始化jedis连接池
     */
    private static void initJedisPool() {
        log.info("JedisPool注入成功！！");
       // log.info("redis地址：" + host + ":" + port);
        GenericObjectPoolConfig jedisPoolConfig = new GenericObjectPoolConfig();
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMaxWaitMillis(10000);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(true);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
       // poolConfig, host, port, timeout, password, 0, (String)null, ssl
        pool = new JedisPool(jedisPoolConfig, "192.168.147.140");
    }

}
