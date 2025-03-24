package com.example.springsecuritydemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;
import java.util.HashMap;

/**
 * 会话并发处理
 */
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {

        HashMap result = new HashMap();
        result.put("code",-1);
        result.put("message","该用户已从其它设备登录");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);

        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
    }
}
