package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 查询所有用户
    @GetMapping("/list")
    @PreAuthorize("hasRole('READER') and authentication.name == 'larry'")
    public List<User> getAllUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        Object credentials = authentication.getCredentials();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return userService.list();
    }

    // 根据ID查询用户
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    // 添加用户
    @PostMapping
    @PreAuthorize("hasRole('EDITOR')")
    public String addUser(@RequestBody User user) {
//        userService.save(user);
        userService.saveUserDetails(user);
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

    private void test() {
        //此参数称为工作因子，范围4-31，默认是11，值越大运算越慢
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
        String encodedPassword = passwordEncoder.encode("123456");
    }
}