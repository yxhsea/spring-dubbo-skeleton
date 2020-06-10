package com.skeleton.foundation.cache;

import redis.clients.jedis.Jedis;

public interface IRedisStandaloneConsumer<T> {
    T handle(Jedis jedis) throws Exception;
}
