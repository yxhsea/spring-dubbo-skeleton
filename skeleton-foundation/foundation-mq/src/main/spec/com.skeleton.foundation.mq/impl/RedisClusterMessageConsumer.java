package com.skeleton.foundation.mq.impl;

import com.skeleton.foundation.mq.IMessageConsumer;
import com.skeleton.foundation.mq.IMessageSerializer;
import com.skeleton.foundation.mq.MessageTopic;
import redis.clients.jedis.*;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 */
public class RedisClusterMessageConsumer implements IMessageConsumer, Serializable {

    private List<String> hosts;
    private IMessageSerializer serializer;
    private JedisCluster cluster;

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public void __init__() {
        Set<HostAndPort> jedisClusterNodes=new HashSet<HostAndPort>();
        for (String host: hosts){
            String[] hostAndPort = host.split(":");
            jedisClusterNodes.add(new HostAndPort(hostAndPort[0], Integer.parseInt(hostAndPort[1])));
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(false);
        poolConfig.setMaxWaitMillis(5000);
        poolConfig.setMaxIdle(64);
        poolConfig.setMaxTotal(1024);
        cluster = new JedisCluster(jedisClusterNodes,100000,10000,10,poolConfig);
    }

    @Override
    public void setSerializer(IMessageSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public <T> List<T> receive(MessageTopic topic, Class<T> messageType, int size) throws Exception {
            List<T> list = new ArrayList<T>();
            byte[] key = topic.getTopicName().getBytes(Charset.forName("UTF-8"));
            for (int i = 0; i < size; i++) {
                byte[] bytes = this.cluster.lpop(key);
                if (bytes != null) {
                    T v = this.serializer.deserialize(bytes, messageType);
                    if (v != null) {
                        list.add(v);
                    }
                }
            }
            return list;
    }
}
