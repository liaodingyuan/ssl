package com.liaody.ssl.controller;

import com.liaody.ssl.config.JedisManager;
import com.liaody.ssl.constants.ResultCode;
import com.liaody.ssl.constants.ResultMessage;
import com.liaody.ssl.redis.redisson.RedissonConsumer;
import com.liaody.ssl.redis.redisson.RedissonProducer;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.concurrent.*;


/**
 * Redisson Client 控制器
 * @author yuanhaha
 */
@RestController
@RequestMapping("/redisson")
@Slf4j
public class RedissonClientController {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").get();

    private static ExecutorService pool = new ThreadPoolExecutor(8, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedissonConsumer redissonConsumer;

    @Autowired
    private RedissonProducer redissonProducer;


    @GetMapping("/one")
    public ResultMessage testOne() throws ExecutionException, InterruptedException {
        RAtomicLong longObject = redissonClient.getAtomicLong("myLong");
        // 同步执行
        boolean b = longObject.compareAndSet(400, 401);
        log.info("compare result--->{}",b);
        // 异步执行
        RFuture<Boolean> booleanRFuture = longObject.compareAndSetAsync(3, 401);
        Boolean aBoolean = booleanRFuture.get();
        log.info("compare result 2--->{}",aBoolean);
        return ResultCode.SUCCESS.withMessage("测试完毕");
    }

    @GetMapping("/tow")
    public ResultMessage testTow(){
        RKeys keys = redissonClient.getKeys();
        // sets all or nothing if some bucket is already exists
        // buckets.trySet(map);
        // store all at once
        // buckets.set(map);
        return ResultCode.SUCCESS.withMessage("测试完毕");
    }

    /**
     * 推送消息
     * @param message 消息
     * @return 推送消息结果
     */
    @PostMapping("/push")
    public ResultMessage redisPushMessage(@RequestParam String message){
        if(StringUtils.isBlank(message)){
         return  ResultCode.BAD_REQUEST_PARAMS.withMessage("不能推送空消息");
        }
        redissonProducer.producer(message);
        return ResultCode.SUCCESS.withData("消息推送成功");
    }

    @PostMapping("/init-bloomFilter")
    public ResultMessage initBloomFilter(){
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("sample");
       // 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
        bloomFilter.tryInit(55000000L, 0.03);

        Jedis jedis = null;
        Pipeline pipeline = null;
        // 获取jedis实例
        jedis = JedisManager.getJedisInstance();
        // 使用pipelining
        pipeline = jedis.pipelined();
        for(int index =0 ;index <50000000;index++){
           // pipeline.
            pipeline.pfadd("filed"+index);
        }
        pipeline.sync();
        try {
            pipeline.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        pool.submit(()->{
//            for(int index = 0;index<50000;index++){
//                log.info("index-->{}",index);
//                bloomFilter.add("filed"+index);
//            }
//        });
//        pool.submit(()->{
//            for(int index = 50000;index<100000;index++){
//                log.info("index-->{}",index);
//                bloomFilter.add("filed"+index);
//            }
//        });
//        pool.submit(()->{
//            for(int index = 100000;index<150000;index++){
//                log.info("index-->{}",index);
//                bloomFilter.add("filed"+index);
//            }
//        });
//        pool.submit(()->{
//            for(int index = 150000;index<200000;index++){
//                log.info("index-->{}",index);
//                bloomFilter.add("filed"+index);
//            }
//        });
        return ResultCode.SUCCESS.withMessage("初始化布隆过滤器成功");
    }

    @GetMapping("/init-bloomFilter")
    public ResultMessage checkMessage(@RequestParam String key){
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("sample");

        return ResultCode.SUCCESS.withData(bloomFilter.contains(key));
    }

}
