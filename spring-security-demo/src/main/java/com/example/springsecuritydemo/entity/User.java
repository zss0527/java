package com.example.springsecuritydemo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer enabled;
}
