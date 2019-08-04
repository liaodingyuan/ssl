//package com.liaody.ssl.cassandra;
//
//import com.datastax.driver.core.*;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//
///**
// * cassandra 工具类、
// *
// * @author yuanhaha
// */
//@Component
//@Slf4j
//public class CassandraUtils {
//
//    private Cluster cluster;
//    private Session session;
//    private static final String KEY_SPACE = "apm";
//
//    @PostConstruct
//    public void init() {
//        // 可以只添加一个seed节点
//        cluster = Cluster.builder().addContactPoints("192.168.147.140").withPort(9042).build();
//        session = cluster.connect(KEY_SPACE);
//        Metadata metadata = cluster.getMetadata();
//        for (Host host : metadata.getAllHosts()) {
//            System.out.println("------" + host.getAddress());
//        }
//        System.out.println("======================");
//        for (KeyspaceMetadata keyspaceMetadata : metadata.getKeyspaces()) {
//            System.out.println("--------" + keyspaceMetadata.getName());
//        }
//    }
//
//
//    /**
//     * 创建cassandra  key space
//     * @param keySpace 键空间
//     */
//    public void createKeySpace(String keySpace) {
//        if (StringUtils.isBlank(keySpace)) {
//            log.error("key space is blank.");
//            return;
//        }
//
//        //Query
//        String query = "CREATE KEYSPACE " + keySpace + " WITH replication "
//                + "= {'class':'SimpleStrategy', 'replication_factor':1} AND DURABLE_WRITES = true;";
//
//        //Executing the query
//        session.execute(query);
//
//        //using the KeySpace
//        session.execute("USE " + keySpace);
//        log.info("Keyspace created or updated");
//    }
//
//    /**
//     * 删除keyspace
//     * @param keySpace 键空间
//     */
//    public void dropKeySpace(String keySpace){
//        if (StringUtils.isBlank(keySpace)) {
//            log.error("key space is blank.");
//            return;
//        }
//        String query = "DROP KEYSPACE "+keySpace+"; ";
//        session.execute(query);
//        log.info("Delete keyspace success.");
//    }
//
//    public void createTable(String keySpace,String tableName){
//        if (StringUtils.isBlank(keySpace) || StringUtils.isBlank(tableName)) {
//            log.error("keyspace or table name is blank.");
//            return;
//        }
//        //Query
//        String query = "CREATE TABLE emp2(emp_id int PRIMARY KEY, "
//                + "emp_name text, "
//                + "emp_city text, "
//                + "emp_sal varint, "
//                + "emp_phone varint );";
//        session.execute(query);
//        log.info("Create table success.");
//
//    }
//
//
//
//
//    /**
//     * 关闭
//     */
//    public void close() {
//        destroy();
//    }
//
//
//    @PreDestroy
//    public void destroy() {
//        try {
//            if (cluster != null) {
//                cluster.close();
//            }
//            if (session != null) {
//                session.close();
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }
//}
