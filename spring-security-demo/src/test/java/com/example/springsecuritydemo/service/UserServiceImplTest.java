package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = userService.getById(1);
        System.out.println(user);
    }
}
