package com.liaody.ssl.util;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.nio.charset.StandardCharsets;

/**
 * zk 的序列化和反序列化
 */
public class MyZkSerializer implements ZkSerializer {

    @Override
    public byte[] serialize(Object obj) throws ZkMarshallingError {
        return String.valueOf(obj).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        return new String(bytes,StandardCharsets.UTF_8);
    }
}
