package com.liaody.ssl.redis.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RTopic;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 使用redis做为队列
 */
@Slf4j
@Component
public class RedissonConsumer {

    private static final String MY_TOPIC = "myTopic";
    @PostConstruct
    public void init(){
        RTopic topic = RedissonManager.getRedissonClient().getTopic(MY_TOPIC);
        // 添加监听器
        // Topic listeners are resubscribed automatically during any reconnection to Redis or Redis server failover.
        topic.addListener(String.class, (channel, message) -> log.info("chanel--->{},message->{}",channel,message));
        log.info("========redis 监听 topic->{} 初始化完毕 ====",MY_TOPIC);

    }
}
