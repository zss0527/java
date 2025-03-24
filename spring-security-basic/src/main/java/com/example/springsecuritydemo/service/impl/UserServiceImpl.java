package com.example.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecuritydemo.config.DBUserDetailsManager;
import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.mapper.UserMapper;
import com.example.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private DBUserDetailsManager dbUserDetailsManager;

    @Override
    public void saveUserDetails(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder().username(user.getUsername()).password(user.getPassword()).build();
        dbUserDetailsManager.createUser(userDetails);
    }
}
