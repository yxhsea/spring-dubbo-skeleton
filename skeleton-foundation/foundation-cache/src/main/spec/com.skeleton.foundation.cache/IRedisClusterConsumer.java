package com.skeleton.foundation.cache;


import redis.clients.jedis.JedisCluster;

public interface IRedisClusterConsumer<T> {
    T handle(JedisCluster jedisCluster) throws Exception;
}
