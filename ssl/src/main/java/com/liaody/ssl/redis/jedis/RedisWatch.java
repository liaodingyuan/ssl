package com.liaody.ssl.redis.jedis;

import com.liaody.ssl.config.JedisManager;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * 基于redis watch的乐观锁，实现高并发秒杀
 */
@Component
public class RedisWatch {

    public static void testWatch() throws Exception {
        Jedis jedis = null;
        try {
// 获取jedis连接
            jedis = JedisManager.getJedisInstance();

            // 抢到的用户
            String key_s = "user_name";
            // 商品数量
            String key = "test_count";
            // 用户名字
            String clientName = UUID.randomUUID().toString().replace("-", "");

            while (true) {
                try {
                    // key加上乐观锁
                    jedis.watch(key);
                    System.out.println("用户:" + clientName + "开始抢商品");
                    System.out.println("当前商品的个数：" + jedis.get(key));
                    // 当前商品个数
                    int prdNum = Integer.parseInt(jedis.get(key));
                    if (prdNum > 0) {
// 标记一个事务块的开始
                        Transaction transaction = jedis.multi();
                        transaction.set(key, String.valueOf(prdNum - 1));
                        // 原子性提交事物
                        List<Object> result = transaction.exec();
                        if (result == null || result.isEmpty()) {
                            // 可能是watch-key被外部修改，或者是数据操作被驳回
                            System.out.println("用户:" + clientName + "没有抢到商品");
                        } else {
                            // 将抢到的用户存起来
                            jedis.sadd(key_s, clientName);
                            System.out.println("用户:" + clientName + "抢到商品");
                            break;
                        }
                    } else {
                        System.out.println("库存为0，用户:" + clientName + "没有抢到商品");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // exec，discard，unwatch命令都会清除连接中的所有监视
                    jedis.unwatch();

                }
            } // while
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("redis bug:" + e.getMessage());
        } finally {
            // 释放jedis连接
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println("redis bug:" + e.getMessage());
                // TODO Auto-generated catch block

            }
        }

    }


}
