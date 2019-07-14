package com.liaody.ssl.cassandra;

import com.datastax.driver.core.*;

import java.util.Iterator;


public class CassandraTest {

    private static Cluster cluster;
    private Session session;

    public static void main(String[] args) {
        test3();
    }

    private static void test1() {
        Cluster cluster =
                Cluster.builder().addContactPoints("192.168.147.140").withPort(9042).build();

        Metadata metadata = cluster.getMetadata();
        for (Host host : metadata.getAllHosts()) {
            System.out.println("------" + host.getAddress());
        }
        System.out.println("======================");
        for (KeyspaceMetadata keyspaceMetadata : metadata.getKeyspaces()) {
            System.out.println("--------" + keyspaceMetadata.getName());
        }
        cluster.close();
    }

    public static void test2(){
        Cluster cluster =
                Cluster.builder().addContactPoints("192.168.147.140").withPort(9042).build();
        Session session = cluster.connect();
        String cql = "select * from apm.data1;";
        ResultSet resultSet = session.execute(cql);
        Iterator<Row> iterator = resultSet.iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            System.out.println(row.getObject("phone"));
        }
        session.close();
        cluster.close();
    }

    /**
     * 官方推荐
     */
    public static void test3(){
        Cluster cluster =
                Cluster.builder().addContactPoints("192.168.147.140").withPort(9042).build();
        Session session = cluster.connect();
        SimpleStatement statement =
                new SimpleStatement("SELECT release_version FROM system.local");
        ResultSet resultSet = session.execute(statement);
        Row row = resultSet.one();
        System.out.println(row.getString("release_version"));
        session.close();
        cluster.close();
    }

}
