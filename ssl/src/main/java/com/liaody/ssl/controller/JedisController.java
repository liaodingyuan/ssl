package com.liaody.ssl.controller;

import com.liaody.ssl.constants.ResultCode;
import com.liaody.ssl.constants.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import javax.xml.ws.soap.Addressing;

@RestController
@RequestMapping("/jedis")
@Slf4j
public class JedisController {

    @Autowired
    private JedisCluster jedisCluster;

    @PostMapping("/set-key")
    public ResultMessage setKey(@RequestParam String key,@RequestParam String value){
        String set = jedisCluster.set(key, value);
        log.info("set-key value response->{}",set);
        return ResultCode.SUCCESS.withMessage("数据处理完毕");
    }

}
