package com.skeleton.foundation.cache;

public interface IRedisResourceProvider {
    <K> K consume(IRedisClusterConsumer<K> consumer);
    <K> K consume(IRedisStandaloneConsumer<K> consumer);
}
