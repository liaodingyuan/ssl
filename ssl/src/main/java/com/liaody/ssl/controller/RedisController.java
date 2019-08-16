package com.liaody.ssl.controller;

import com.google.common.collect.Sets;
import com.liaody.ssl.constants.ResultCode;
import com.liaody.ssl.constants.ResultMessage;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Redis测试用Controller
 * @author yuanhaha
 */
@RestController
public class RedisController {

    @Autowired(required = false)
    public RedissonClient redissonClient;

    @GetMapping("/get-key")
    @ApiOperation(value = "获取普通的key-value", notes = "EBucket")
    public ResultMessage getKey(@RequestParam String key){
        if(StringUtil.isBlank(key)){
            return ResultCode.BAD_REQUEST_PARAMS.withMessage("redis key require");
        }
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return ResultCode.SUCCESS.withData(bucket.get());
    }

    @GetMapping("/get-list")
    @ApiOperation(value = "获取list", notes = "RList")
    public ResultMessage getList(@RequestParam String key){
        if(StringUtil.isBlank(key)){
            return ResultCode.BAD_REQUEST_PARAMS.withMessage("redis key require");
        }
        RList<Object> list = redissonClient.getList(key);
        List<Object> range = list.range(0, list.size());
        return ResultCode.SUCCESS.withData(range);
    }

    @GetMapping("/get-map")
    @ApiOperation(value = "获取map", notes = "RMap")
    public ResultMessage getMap(@RequestParam(required = false) String key){
        if(StringUtil.isBlank(key)){
            //return ResultCode.BAD_REQUEST_PARAMS.withMessage("redis key require");
            key = "website";
        }
        RMap<Object, Object> map = redissonClient.getMap(key);
        return ResultCode.SUCCESS.withData(map.getAll(Sets.newHashSet("google","baidu")));
    }
}
