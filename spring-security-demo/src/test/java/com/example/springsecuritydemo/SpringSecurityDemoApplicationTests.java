package com.example.springsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

@SpringBootTest
class SpringSecurityDemoApplicationTests {

	@Test
	void contextLoads() {
		//此参数称为工作因子，范围4-31，默认是11，值越大运算越慢
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
		String encodedPassword = passwordEncoder.encode("123456");
		System.out.println("encodedPassword: " + encodedPassword);
		Assert.isTrue(passwordEncoder.matches("123456", encodedPassword),"password not equal");
	}

}
