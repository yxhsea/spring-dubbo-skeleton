package com.skeleton.foundation.mq.impl;

import com.skeleton.foundation.mq.IMessageProducer;
import com.skeleton.foundation.mq.IMessageSerializer;
import com.skeleton.foundation.mq.MessageTopic;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.nio.charset.Charset;

/**
 */
public class RedisMessageProducer implements IMessageProducer {

    private String host;
    private int port;
    private IMessageSerializer serializer;
    private JedisPool pool;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void __init__() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(false);
        poolConfig.setMaxWaitMillis(5000);
        poolConfig.setMaxIdle(64);
        poolConfig.setMaxTotal(1024);
        this.pool = new JedisPool(poolConfig, this.host, this.port, 100000);
    }


    @Override
    public void setSerializer(IMessageSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public void send(MessageTopic topic, Object... messages) throws Exception {
        Jedis resource = null;
        try {
            resource = this.pool.getResource();
            byte[] key = topic.getTopicName().getBytes(Charset.forName("UTF-8"));
            Pipeline pipelined = resource.pipelined();
            if (messages != null) for (Object msg : messages) {
                byte[] bytes = this.serializer.serialize(msg);
                pipelined.lpush(key, bytes);
            }
            pipelined.sync();
        }finally {
            if (resource!=null)
                resource.close();
        }
    }
}
