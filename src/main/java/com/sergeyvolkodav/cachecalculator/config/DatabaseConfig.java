package com.sergeyvolkodav.cachecalculator.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class DatabaseConfig {

    @Bean
    public RedisConnectionFactory connectionFactory() {
        return redisConnectionFactory();

    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("calculator.sergeyvolkodav.com");
        jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory;
    }

//    @Bean
//    public RedisSentinelConfiguration sentinelConfig() {
//        return new RedisSentinelConfiguration().master("master")
//                .sentinel("calculator.sergeyvolkodav.com", 5000)
//                .sentinel("calculator.sergeyvolkodav.com", 5001);
//    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }
}
