package com.liaody.ssl.redis.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * Redis分布式锁
 */
@Slf4j
public class DistributedRedisLock {

    private static RedissonClient redissonClient = RedissonManager.getRedissonClient();
    private static final String LOCK_TITLE = "redisLock_";

    public static void acquire(String lockName){
        String key =  LOCK_TITLE+lockName;
        RLock myock = redissonClient.getLock(key);
        // Redis锁时间设置为两分钟。防止死锁
        myock.lock(2, TimeUnit.MINUTES);
        log.info("======lock======"+Thread.currentThread().getName());
    }
    public static void release(String lockName){
        String key = LOCK_TITLE + lockName;
        RLock mylock = redissonClient.getLock(key);
        mylock.unlock();
        log.info("======unlock======"+Thread.currentThread().getName());
    }

    private static void redisLock(){
        RedissonManager.init(); //初始化
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                try {
                    String key = "test123";
                    DistributedRedisLock.acquire(key);
                    //获得锁之后可以进行相应的处理
                    Thread.sleep(1000);
                    log.info("======获得锁后进行相应的操作======");
                    DistributedRedisLock.release(key);
                    log.info("=============================");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }

    public static void main(String[] args) {
        redisLock();
    }
}
