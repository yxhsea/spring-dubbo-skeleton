package com.skeleton.foundation.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class StandaloneRedisCacheService implements IRedisCacheService {
    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private IRedisResourceProvider redisResourceProvider;
    private final Logger logger = LoggerFactory.getLogger(ClusterRedisCacheService.class);

    public StandaloneRedisCacheService(IRedisResourceProvider redisResourceProvider) {
        this.redisResourceProvider = redisResourceProvider;
    }
    @Override
    public Boolean set(String key, String value) {
        return redisResourceProvider.consume(new IRedisStandaloneConsumer<Boolean>() {
            @Override
            public Boolean handle(Jedis jedis) throws Exception {
                try {
                    jedis.set(key,value);
                    return true;
                }catch (Exception e){
                    return false;
                }

            }
        });
    }

    @Override
    public Boolean set(String key, String value, int expireSeconds) {
        return redisResourceProvider.consume(new IRedisStandaloneConsumer<Boolean>() {
            @Override
            public Boolean handle(Jedis jedis) throws Exception {
                try {
                    jedis.set(key,value);
                    if (expireSeconds != 0) {
                        jedis.expire(key, expireSeconds);
                    }
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        });
    }

    @Override
    public String get(String key) {
        return redisResourceProvider.consume(new IRedisStandaloneConsumer<String>() {
            @Override
            public String handle(Jedis jedis) throws Exception {
                   return jedis.get(key);
            }
        });
    }

    @Override
    public boolean delete(String key) {
        return redisResourceProvider.consume(new IRedisStandaloneConsumer<Boolean>() {
            @Override
            public Boolean handle(Jedis jedis) throws Exception {
                return jedis.del(key) >= 1;
            }
        });
    }

    @Override
    public Boolean setnx(String key, String value) {
        return redisResourceProvider.consume(new IRedisStandaloneConsumer<Boolean>() {
            @Override
            public Boolean handle(Jedis jedis) throws Exception {
                try {
                    jedis.setnx(key,value);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        });
    }

    @Override
    public Long incr(String key) {
        return redisResourceProvider.consume(new IRedisStandaloneConsumer<Long>() {
            @Override
            public Long handle(Jedis jedis) throws Exception {
                return jedis.incr(key);
            }
        });

    }

    @Override
    public byte[] getObject(byte[] key) {

        return redisResourceProvider.consume(new IRedisStandaloneConsumer<byte[]>() {
            @Override
            public byte[] handle(Jedis jedis) throws Exception {
                return jedis.get(key);
            }
        });
    }

    @Override
    public Boolean setObject(byte[] key, byte[] value, int expireSeconds) {

        return redisResourceProvider.consume(new IRedisStandaloneConsumer<Boolean>() {
            @Override
            public Boolean handle(Jedis jedis) throws Exception {
                try {
                    jedis.set(key,value);
                    if (expireSeconds != 0) {
                        jedis.expire(key, expireSeconds);
                    }
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        });
    }

    @Override
    public Boolean updateObject(byte[] key, int expireSeconds){
          return redisResourceProvider.consume(new IRedisStandaloneConsumer<Boolean>() {
              @Override
              public Boolean handle(Jedis jedis) throws Exception {
                  try {
                      jedis.expire(key, expireSeconds);
                      return true;
                  } catch (Exception ex) {
                      return false;
                  }
              }
          });
    }

    @Override
    public Boolean updateExpireTime(String key, int expireSeconds) {

        return redisResourceProvider.consume(new IRedisStandaloneConsumer<Boolean>() {
            @Override
            public Boolean handle(Jedis jedis) throws Exception {
                try {
                    jedis.expire(key, expireSeconds);
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });
    }

    @Override
    public long getCountByKey(String key) {
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

    @Override
    public long hset(String key, String field, String value) {
        return this.redisResourceProvider.consume(new IRedisStandaloneConsumer<Long>() {
            @Override
            public Long handle(Jedis jedis) throws Exception {
                try {
                    return jedis.hset(key, field, value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException("Redis 'hset' operation exception. ");
                }
            }
        });
    }

    @Override
    public String hset(String key, Map<String, String> map) {
        return this.redisResourceProvider.consume(new IRedisStandaloneConsumer<String>() {
            @Override
            public String handle(Jedis jedis) throws Exception {
                try {
                    return jedis.hmset(key, map);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException("Redis 'hset' operation exception. ");
                }
            }
        });
    }

    @Override
    public String hget(String key, String field) {
        return this.redisResourceProvider.consume(new IRedisStandaloneConsumer<String>() {
            @Override
            public String handle(Jedis jedis) throws Exception {
                try {
                    return jedis.hget(key, field);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException("Redis 'hset' operation exception. ");
                }
            }
        });
    }

    @Override
    public Map<String, String> hgetAll(String key, String field) {
        return this.redisResourceProvider.consume(new IRedisStandaloneConsumer<Map<String, String>>() {
            @Override
            public Map<String, String> handle(Jedis jedis) throws Exception {
                try {
                    return jedis.hgetAll(key);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException("Redis 'hset' operation exception. ");
                }
            }
        });
    }
}
