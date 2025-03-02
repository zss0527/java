package com.example.redislearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

@Service
public class JedisService {
    private final JedisCluster jedisCluster;

    public JedisService(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public String get(String key) {
        return jedisCluster.get(key);
    }

    public void set(String key, String value) {
        jedisCluster.set(key,value);
    }

    public void delete(String key) {
        jedisCluster.del(key);
    }

    public boolean exists(String key) {
        return jedisCluster.exists(key);
    }
}
