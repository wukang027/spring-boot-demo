package com.jm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class RedisConfig {
    @Value("${ip}")
    private String ip;
    @Value("${redis.port}")
    private Integer port;

    @Bean(name= "jedis.pool")  
    @Autowired
    public JedisPool jedisPool(){
        return new JedisPool(ip,port);
    }  
      
    @Bean(name= "jedis.pool.config")  
    public JedisPoolConfig jedisPoolConfig () {
        JedisPoolConfig config = new JedisPoolConfig();  
        return config;  
    }  
}
