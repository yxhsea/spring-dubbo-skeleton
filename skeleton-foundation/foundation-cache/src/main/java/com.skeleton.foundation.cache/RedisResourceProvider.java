package com.skeleton.foundation.cache;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.*;

import java.util.*;

public class RedisResourceProvider implements IRedisResourceProvider {
    private List<String> hosts;
    private JedisPool pool;
    private JedisCluster cluster;
    private String passwd;

    public RedisResourceProvider(List<String> hosts) {
        this.hosts = hosts;
    }

    public RedisResourceProvider(List<String> hosts,String passwd) {
        this.hosts = hosts;
        this.passwd = passwd;
    }

    private synchronized JedisPool getJedisPool() {
        if (this.pool == null) {
            List<HostAndPort> jedisClusterNodes = new ArrayList();
            Iterator iterator = this.hosts.iterator();

            while(iterator.hasNext()) {
                String host = (String)iterator.next();
                String[] hostAndPort = host.split(":");
                jedisClusterNodes.add(new HostAndPort(hostAndPort[0], Integer.parseInt(hostAndPort[1])));
            }

            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setTestOnBorrow(true);
            poolConfig.setTestOnReturn(false);
            poolConfig.setMaxWaitMillis(5000L);
            poolConfig.setMaxIdle(64);
            poolConfig.setMaxTotal(1024);
            if (StringUtils.isNotBlank(passwd)) {
                this.pool = new JedisPool(poolConfig, ((HostAndPort) jedisClusterNodes.get(0)).getHost(), ((HostAndPort) jedisClusterNodes.get(0)).getPort(), 100000,passwd);
            }else {
                this.pool = new JedisPool(poolConfig, ((HostAndPort) jedisClusterNodes.get(0)).getHost(), ((HostAndPort) jedisClusterNodes.get(0)).getPort(), 100000);
            }
            }

        return this.pool;
    }

    private synchronized JedisCluster getJedisCluster() {
        if (this.pool == null) {
            Set<HostAndPort> jedisClusterNodes = new HashSet();
            Iterator it = this.hosts.iterator();

            while(it.hasNext()) {
                String host = (String)it.next();
                String[] hostAndPort = host.split(":");
                jedisClusterNodes.add(new HostAndPort(hostAndPort[0], Integer.parseInt(hostAndPort[1])));
            }

            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setTestOnBorrow(true);
            poolConfig.setTestOnReturn(false);
            poolConfig.setMaxWaitMillis(5000L);
            poolConfig.setMaxIdle(64);
            poolConfig.setMaxTotal(1024);
            if (StringUtils.isNotBlank(passwd)) {
                this.cluster = new JedisCluster(jedisClusterNodes, 100000, 10000, 10, passwd, poolConfig);
            }else {
                this.cluster = new JedisCluster(jedisClusterNodes, 100000, 10000, 10, poolConfig);
            }
            }

        return this.cluster;
    }

    @Override
    public <K> K consume(IRedisClusterConsumer<K> consumer) {
        JedisCluster c = this.getJedisCluster();

        try {
            return consumer.handle(c);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Redis cluster operation exception.");
        }
    }

    @Override
    public <K> K consume(IRedisStandaloneConsumer<K> consumer) {
        JedisPool p = this.getJedisPool();
        Jedis jedis = null;

        try {
            jedis = p.getResource();
            K object = consumer.handle(jedis);
            return object;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return null;
    }
}
