package com.example.redislearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class DataRedisService {
    private final RedisTemplate redisTemplate;

    public DataRedisService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void dataRedis() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("dataRedisTest", "this is dataRedisTest value");
        String value = valueOperations.get("dataRedisTest").toString();
        System.out.println("dataRedisTest: "+value);
    }
}
