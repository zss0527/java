package com.example.springsecuritydemo.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity//开启spring security的自定义配置（在springboot项目中这个注解可以省略）
public class WebSecurityConfig {

    /**
     * 用户认证
     * @return
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //基于内存的用户认证
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        //JdbcUserDetailsManager只能基于jpa，mybatis没法直接使用
//
//        return manager;
//    }

//    @Bean
//    public UserDetailsManager userDetailsManager() {
//        //自定义适合mybatis的基于数据库的用户认证
//        return new DBUserDetailsManager();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest()
                        .authenticated()
                )
//                .formLogin(Customizer.withDefaults())//default login
                .formLogin(form -> {
                    form.loginPage("/login").permitAll()//use self designed login page, don't need auth for /login
                            .usernameParameter("myusername")
                            .passwordParameter("mypassword")
                            .failureUrl("/login?failure");
                })
                .httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
