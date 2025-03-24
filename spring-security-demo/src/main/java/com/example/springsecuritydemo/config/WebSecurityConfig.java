package com.example.springsecuritydemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;

@Configuration
//@EnableWebSecurity//开启spring security的自定义配置（在springboot项目中这个注解可以省略）
@EnableMethodSecurity(prePostEnabled = true)//基于方法的授权
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
                        //基于request的授权
//                        .requestMatchers("/users/list").hasAuthority("USER_LIST")
//                        .requestMatchers("/users/add").hasAuthority("USER_ADD")
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        //对所有请求开启授权保护
                        .anyRequest()
                        //已认证的请求会被自动授权
                        .authenticated()
                )
//                .formLogin(Customizer.withDefaults())//default login
                .formLogin(form -> {
                    form.loginPage("/login").permitAll()//use self designed login page, don't need auth for /login
                            .usernameParameter("myusername")
                            .passwordParameter("mypassword")
                            .failureUrl("/login?failure")
                            .successHandler(new MyAuthenticationSuccessHandler())
                            .failureHandler(new MyAuthenticationFailureHandler());
                })
                .logout(logout -> logout.logoutSuccessHandler(new MyLogoutSuccessHandler()))
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());
                    exception.accessDeniedHandler((request, response, accessDeniedException) -> {
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("error",accessDeniedException.getMessage());
                        map.put("code",-1);

                        ObjectMapper objectMapper = new ObjectMapper();
                        String s = objectMapper.writeValueAsString(map);
                        response.getWriter().println(s);
                    });
                })
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> {
                    session.maximumSessions(1).expiredSessionStrategy(new MySessionInformationExpiredStrategy());
                });
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
