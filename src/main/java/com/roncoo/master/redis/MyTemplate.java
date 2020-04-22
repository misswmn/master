package com.roncoo.master.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/4/22 17:47
 */
@Configuration
public class MyTemplate {

    @Bean
    public StringRedisTemplate customStringRedisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(connectionFactory);
        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return stringRedisTemplate;
    }
}
