//package yuanhaha.zk.admin.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//@Configuration
//@Slf4j
//public class RedissonConf {
//
//    @Value("${redis.server.master}")
//    private String redisMaster;
//    @Value("${redis.server.nodes}")
//    private String redisNodes;
//
//    @Bean
//    public RedissonClient buildRedissonClient() {
//        Config config = new Config();
//        String[] nodes = redisNodes.split(",");
//        log.info("redis master:{} slaves:{}",redisMaster,nodes);
//        config.useMasterSlaveServers()
//                .setConnectTimeout(50000)
//                //可以用"rediss://"来启用SSL连接
//                .setMasterAddress(redisMaster)
//                .addSlaveAddress(nodes[0], nodes[1]);
//        return Redisson.create(config);
//    }
//
//}
