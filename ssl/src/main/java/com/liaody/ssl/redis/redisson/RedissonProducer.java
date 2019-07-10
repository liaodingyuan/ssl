package com.liaody.ssl.redis.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.springframework.stereotype.Component;

/**
 * 使用redis作为队列
 */
@Component
@Slf4j
public class RedissonProducer {

    public static final String MY_TOPIC = "myTopic";

    public long producer(String message){
        RTopic  topic = RedissonManager.getRedissonClient().getTopic(MY_TOPIC);
        long publish = topic.publish(message);
        log.info("clients received message --->{} result---> {}",message,publish);
        return publish;
    }

}
