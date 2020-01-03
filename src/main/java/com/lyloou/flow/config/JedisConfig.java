package com.lyloou.flow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author lyloou
 */
@Component
public class JedisConfig {

    @Value("${redis.server}")
    String server;
    @Value("${redis.port}")
    int port;
    @Value("${redis.password}")
    String password;
    @Value("${redis.timeout}")
    int timeout;
    @Value("${redis.maxTotal}")
    int maxTotal;

    @Bean
    public Jedis jedis() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        config.setMaxTotal(maxTotal);
        return new JedisPool(config, server, port, timeout, password).getResource();
    }
}
