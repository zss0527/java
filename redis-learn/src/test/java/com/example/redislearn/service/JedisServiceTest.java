package com.example.redislearn.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JedisServiceTest {
    @Autowired
    JedisService jedisService;

    @Test
    public void testJedisCluster() {
        jedisService.set("testKey","testValue");
        String value = jedisService.get("testKey");
        System.out.println("testKey: " + value);
        jedisService.delete("testKey");
        boolean exists = jedisService.exists("testKey");
        System.out.println("testKey exists? " + exists);
    }
}
