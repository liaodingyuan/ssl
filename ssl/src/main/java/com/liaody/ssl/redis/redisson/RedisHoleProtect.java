package com.liaody.ssl.redis.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 预防缓存击穿
 */
@Component
@Slf4j
public class RedisHoleProtect {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 使用互斥锁预防缓存击穿。至于锁的类型，单机环境用并发包的Lock类型就行，集群环境则使用分布式锁( redis的setnx)
     *  该方法是比较普遍的做法，即，在根据key获得的value值为空时，先锁上，再从数据库加载，加载完毕，释放锁。若其他线程发现获取锁失败，则睡眠50ms后重试。
     */
    public void exclusionLock( String key){
        // 获取通用对象桶
        RBucket<Object> bucket = redissonClient.getBucket(key);
        if(null== bucket){
            //if (redissonClient.getLock())
        }



    }

}
