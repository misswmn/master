package com.roncoo.master;

import com.roncoo.master.redis.TestRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MasterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(MasterApplication.class, args);
        TestRedis testRedis = configurableApplicationContext.getBean(TestRedis.class);
        testRedis.test();
        configurableApplicationContext.close();
    }

}
