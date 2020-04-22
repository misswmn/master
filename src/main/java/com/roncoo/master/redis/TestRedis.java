package com.roncoo.master.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Qualifier("customStringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void test() {
//        redisTemplate.opsForValue().set("hello", "我爱你");
//        LOGGER.info("{}", redisTemplate.opsForValue().get("hello"));
//
//        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
//        redisConnection.set("hello01".getBytes(), "坎坎坷坷".getBytes());
//        String result = new String(redisConnection.get("hello01".getBytes()));
//        LOGGER.info("{}", result);

//        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
//        hashOperations.put("sean", "name", "希恩");
//        hashOperations.put("sean", "age", "38");
//        LOGGER.info("{}", hashOperations.entries("sean"));


//        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        Person person = new Person("李四", 11);
        Jackson2HashMapper hashMapper = new Jackson2HashMapper(objectMapper, false);
        stringRedisTemplate.opsForHash().putAll("cat", hashMapper.toHash(person));
        Map personMap = stringRedisTemplate.opsForHash().entries("cat");
        Person cat = objectMapper.convertValue(personMap, Person.class);
        LOGGER.info("cat:{}", cat);
    }

    public void testPubSub() throws InterruptedException {
//        stringRedisTemplate.convertAndSend("ooxx", "hello");
//        stringRedisTemplate.convertAndSend("ooxx", "哈哈");

        RedisConnection redisConnection = stringRedisTemplate.getConnectionFactory().getConnection();
        redisConnection.subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                LOGGER.info("收到消息:" + new String(message.getBody()));
            }
        }, "ooxx".getBytes());

        while (true) {
            stringRedisTemplate.convertAndSend("ooxx", "我自己发的............");
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
