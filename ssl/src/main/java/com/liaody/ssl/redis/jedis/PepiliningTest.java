package com.liaody.ssl.redis.jedis;

import com.liaody.ssl.config.JedisManager;
import com.liaody.ssl.redis.redisson.RedissonManager;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.IOException;

/**
 * 测试redis
 */
public class PepiliningTest {

    public static void main(String[] args) {
        Jedis jedis = null;
        Pipeline pipeline = null;
        // 获取jedis实例
        jedis = JedisManager.getJedisInstance();
        // 使用pipelining
        pipeline = jedis.pipelined();
        // 开始时间
        long startTime  = System.currentTimeMillis();

        // 删除lists
        pipeline.del("lists");
        // 循环添加
        for(int index = 0 ;index<10000;index++){
            pipeline.rpush("lists",index+"");
        }
        pipeline.sync();
        long endTime = System.currentTimeMillis();
        long castTime = endTime - startTime;
        System.out.print("cast time "+castTime+" ms");
        try {
            pipeline.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jedis.close();
    }


}
