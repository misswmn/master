package com.roncoo.master.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.Date;

/**
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/6/9 18:07
 */
@Configuration
public class ElasticsearchTest {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void test() {
        User user = new User();
        user.setId(1L);
        user.setUserName("张三");
        Date now = new Date();
        user.setBirthday(new Date(now.getTime() - 1000000));
        user.setAge(29);
        elasticsearchRestTemplate.putMapping(User.class);
    }
}
