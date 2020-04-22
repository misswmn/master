package com.roncoo.master.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/4/22 16:55
 */
@Component
public class TestRedis {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRedis.class);
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void test() {
        redisTemplate.opsForValue().set("hello", "我爱你");
        LOGGER.info("{}",redisTemplate.opsForValue().get("hello"));
    }
}
