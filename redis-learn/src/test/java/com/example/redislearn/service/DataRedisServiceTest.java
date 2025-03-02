package com.example.redislearn.service;

import com.example.redislearn.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
public class DataRedisServiceTest {
    @Autowired
    DataRedisService dataRedisService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testDataRedis() {
        dataRedisService.dataRedis();
        redisTemplate.opsForValue().set("name", "帅哥");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name:"+name);
        User user = new User();
        user.setName("zhushuaishuai");
        user.setAge(29);
        user.setAddr("shanghai");
        redisTemplate.opsForValue().set("user",user);
        User u = (User)redisTemplate.opsForValue().get("user");
        System.out.println("u = " + u);
    }

    @Test
    public void testStandardUsage() throws JsonProcessingException {
        User user = new User();
        user.setName("Larry");
        user.setAge(29);
        user.setAddr("shanghai");

        String jsonUser = mapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user:02",jsonUser);

        String s = stringRedisTemplate.opsForValue().get("user:02");
        User user01 = mapper.readValue(s,User.class);
        System.out.println("user01 = " + user01);
    }

    @Test
    public void testHash() {
        stringRedisTemplate.opsForHash().put("user:03", "name", "帅哥");
        stringRedisTemplate.opsForHash().put("user:03", "age", "29");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:03");
        System.out.println("entries = " + entries);
    }
}
