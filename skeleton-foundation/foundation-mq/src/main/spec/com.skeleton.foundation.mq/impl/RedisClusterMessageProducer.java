package com.skeleton.foundation.mq.impl;

import com.skeleton.foundation.mq.IMessageProducer;
import com.skeleton.foundation.mq.IMessageSerializer;
import com.skeleton.foundation.mq.MessageTopic;
import redis.clients.jedis.*;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 */
public class RedisClusterMessageProducer implements IMessageProducer {

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
    public void send(MessageTopic topic, Object... messages) throws Exception {
            byte[] key = topic.getTopicName().getBytes(Charset.forName("UTF-8"));
            if (messages != null) for (Object msg : messages) {
                byte[] bytes = this.serializer.serialize(msg);
                this.cluster.lpush(key, bytes);
            }
        }
}
