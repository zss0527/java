package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 查询所有用户
    @GetMapping
    public List<User> getAllUsers() {
        return userService.list();
    }

    // 根据ID查询用户
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    // 添加用户
    @PostMapping
    public String addUser(@RequestBody User user) {
        userService.save(user);
        return "User added successfully!";
    }

    // 更新用户
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.updateById(user);
        return "User updated successfully!";
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.removeById(id);
        return "User deleted successfully!";
    }
}