package com.skeleton.foundation.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisCluster;

import java.util.Map;

public class ClusterRedisCacheService implements IRedisCacheService {
    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private IRedisResourceProvider redisResourceProvider;
    private final Logger logger = LoggerFactory.getLogger(ClusterRedisCacheService.class);

    public ClusterRedisCacheService(IRedisResourceProvider redisResourceProvider) {
        this.redisResourceProvider = redisResourceProvider;
    }

    /**
     * 添加缓存
     * @param key
     * @param value
     * @return
     */
    @Override
    public Boolean set(String key, String value) {
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Boolean>() {
            @Override
            public Boolean handle(JedisCluster resource) throws Exception {
                try {
                    resource.set(key, value);
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });
    }

    /**
     * 添加缓存并设置过期时间
     * @param key
     * @param value
     * @param expireSeconds
     * @return
     */
    @Override
    public Boolean set(String key, String value, int expireSeconds) {
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Boolean>() {
            @Override
            public Boolean handle(JedisCluster resource) throws Exception {
                try {
                    resource.set(key, value);
                    if (expireSeconds != 0) {
                        resource.expire(key, expireSeconds);
                    }
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });
    }

    /**
     * 查询缓存
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<String>() {
            @Override
            public String handle(JedisCluster resource) throws Exception {
                return resource.get(key);
            }
        });
    }

    /**
     * 删除缓存
     * @param key
     * @return
     */
    @Override
    public boolean delete(String key) {
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Boolean>() {
            @Override
            public Boolean handle(JedisCluster resource) throws Exception {
                return resource.del(key) >= 1;
            }
        });
    }

    /**
     * 设置缓存如果不存在
     * @param key
     * @param value
     * @return
     */
    @Override
    public Boolean setnx(String key, String value) {
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Boolean>() {
            @Override
            public Boolean handle(JedisCluster resource) throws Exception {
                try {
                    resource.setnx(key, value);
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });
    }

    /**
     * 缓存计数加1
     * @param key
     * @return
     */
    @Override
    public Long incr(String key) {
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Long>() {
            @Override
            public Long handle(JedisCluster resource) throws Exception {
                try {
                    return resource.incr(key);
                } catch (Exception ex) {
                    return null;
                }
            }
        });
    }
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
    @Override
    public byte[] getObject(byte[] key) {
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<byte[]>() {
            @Override
            public byte[] handle(JedisCluster resource) throws Exception {
                return resource.get(key);
            }
        });
    }
    /**
     * 添加缓存并设置过期时间
     * @param key
     * @param value
     * @param expireSeconds
     * @return
     */
    @Override
    public Boolean setObject(byte[] key, byte[] value, int expireSeconds) {
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Boolean>() {
            @Override
            public Boolean handle(JedisCluster resource) throws Exception {
                try {
                    resource.set(key, value);
                    if (expireSeconds != 0) {
                        resource.expire(key, expireSeconds);
                    }
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });
    }
    /**
     * 添加缓存并设置过期时间
     * @param key
     * @param expireSeconds
     * @return
     */
    @Override
    public Boolean updateObject(byte[] key, int expireSeconds) {
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Boolean>() {
            @Override
            public Boolean handle(JedisCluster resource) throws Exception {
                try {
                    resource.expire(key, expireSeconds);
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });
    }
    /**
     * 添加缓存并设置过期时间
     * @param key
     * @param expireSeconds
     * @return
     */
    @Override
    public Boolean updateExpireTime(String key, int expireSeconds) {
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Boolean>() {
            @Override
            public Boolean handle(JedisCluster resource) throws Exception {
                try {
                    resource.expire(key, expireSeconds);
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });
    }

    @Override
    public long getCountByKey(String key){
        long count = 0;
        String cacheValue = this.get(key);
        if(StringUtils.isNotBlank(cacheValue)){
            try{
                count = Long.valueOf(cacheValue);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     * 添加hash 键值
     * @param key
     * @param field
     * @param value
     * @return
     */
    @Override
    public long hset(String key, String field, String value){
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Long>() {
            @Override
            public Long handle(JedisCluster resource) throws Exception {
                try {
                    return resource.hset(key, field, value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException("Redis 'hset' operation exception. ");
                }
            }
        });
    }

    /**
     * 添加hash列表
     * @param key
     * @param map
     * @return
     */
    @Override
    public String hset(String key, Map<String,String> map){
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<String>() {
            @Override
            public String handle(JedisCluster resource) throws Exception {
                try {
                    return resource.hmset(key, map);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException("Redis 'hset' operation exception. ");
                }
            }
        });
    }

    @Override
    public String hget(String key, String field){
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<String>() {
            @Override
            public String handle(JedisCluster resource) throws Exception {
                try {
                    return resource.hget(key, field);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException("Redis 'hset' operation exception. ");
                }
            }
        });
    }

    @Override
    public Map<String, String> hgetAll(String key, String field){
        return this.redisResourceProvider.consume(new IRedisClusterConsumer<Map<String, String>>() {
            @Override
            public Map<String, String> handle(JedisCluster resource) throws Exception {
                try {
                    return resource.hgetAll(key);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException("Redis 'hset' operation exception. ");
                }
            }
        });
    }
}
