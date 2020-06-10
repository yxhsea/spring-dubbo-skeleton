package com.skeleton.foundation.cache;

import java.util.Map;

public interface IRedisCacheService {
    /**
     * 添加缓存
     * @param key
     * @param value
     * @return
     */
    public Boolean set(String key, String value);

    /**
     * 添加缓存并设置过期时间
     * @param key
     * @param value
     * @param expireSeconds
     * @return
     */
    public Boolean set(String key, String value, int expireSeconds);

    /**
     * 查询缓存
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 删除缓存
     * @param key
     * @return
     */
    public boolean delete(String key);

    /**
     * 设置缓存如果不存在
     * @param key
     * @param value
     * @return
     */
    public Boolean setnx(String key, String value);

    /**
     * 缓存计数加1
     * @param key
     * @return
     */
    public Long incr(String key);
//    /**
//     * 尝试获取分布式锁
//     *
//     * @param key
//     *            锁
//     * @param requestId
//     *            请求标识
//     * @param expireTime
//     *            超期时间
//     * @return 是否获取成功
//     */
//    public Boolean tryLock(String key, String requestId, int expireTime) {
//        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Boolean>() {
//            @Override
//            public Boolean handle(JedisCluster resource) throws Exception {
//                try {
//                    String result = resource.set(key, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
//                    if (LOCK_SUCCESS.equals(result)) {
//                        return true;
//                    }
//                    return false;
//                } catch (Exception ex) {
//                    return false;
//                }
//            }
//        });
//    }
//
//    /**
//     * 释放分布式锁
//     *
//     * @param key
//     *            锁
//     * @param requestId
//     *            请求标识
//     * @return 是否释放成功
//     */
//    public Boolean releaseLock(String key, String requestId, int expireTime) {
//        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Boolean>() {
//            @Override
//            public Boolean handle(JedisCluster resource) throws Exception {
//                try {
//                    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//                    Object result = resource.eval(script, Collections.singletonList(key),Collections.singletonList(requestId));
//                    if (RELEASE_SUCCESS.equals(result)) {
//                        return true;
//                    }
//                    return false;
//                } catch (Exception ex) {
//                    return false;
//                }
//            }
//        });
//    }
    /**
     * 查询缓存
     * @param key
     * @return
     */
    public byte[] getObject(byte[] key);
    /**
     * 添加缓存并设置过期时间
     * @param key
     * @param value
     * @param expireSeconds
     * @return
     */
    public Boolean setObject(byte[] key, byte[] value, int expireSeconds);
    /**
     * 添加缓存并设置过期时间
     * @param key
     * @param expireSeconds
     * @return
     */
    public Boolean updateObject(byte[] key, int expireSeconds);
    /**
     * 添加缓存并设置过期时间
     * @param key
     * @param expireSeconds
     * @return
     */
    public Boolean updateExpireTime(String key, int expireSeconds);

    /**
     * @param key
     * @return
     */
    public long getCountByKey(String key);

    /**
     * 添加hash 键值
     * @param key
     * @param field
     * @param value
     * @return
     */
    public long hset(String key, String field, String value);

    /**
     * 添加hash列表
     * @param key
     * @param map
     * @return
     */
    public String hset(String key, Map<String, String> map);

    /**
     * hashget
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field);

    /**
     * hash getall
     * @param key
     * @param field
     * @return
     */
    public Map<String, String> hgetAll(String key, String field);
}
