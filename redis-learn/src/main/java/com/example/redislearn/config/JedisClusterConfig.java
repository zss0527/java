package com.example.redislearn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.*;

@Configuration
public class JedisClusterConfig {

    @Value("${redis.cluster.nodes}")
    private String clusterNode;

    @Value("${redis.timeout}")
    private int timeout;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.pool.max-active}")
    private int maxActive;

    @Value("${redis.pool.max-idle}")
    private int maxIdle;

    @Value("${redis.pool.min-idle}")
    private int minIdle;

    @Bean
    public JedisCluster jedisCluster() {
        // 1. 配置连接池
        ConnectionPoolConfig poolConfig = new ConnectionPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);

        String[] parts = clusterNode.split(":");
        HostAndPort hostAndPort = new HostAndPort(parts[0], Integer.parseInt(parts[1]));

        // 3. 创建 JedisCluster 对象
        if (password != null && !password.isEmpty()) {
            return new JedisCluster(hostAndPort, timeout, timeout,5, password, poolConfig);
        } else {
            return new JedisCluster(hostAndPort, timeout, timeout, 5, poolConfig);
        }
    }


}
