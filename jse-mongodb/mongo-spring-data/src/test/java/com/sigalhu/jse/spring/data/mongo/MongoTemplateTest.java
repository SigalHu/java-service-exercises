package com.sigalhu.jse.spring.data.mongo;

import com.sigalhu.jse.spring.data.mongo.dataobject.BlogInfoDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author huxujun
 * @date 2018/11/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-data-mongo-test.xml")
public class MongoTemplateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void insert() {
        BlogInfoDO blogInfoDO = BlogInfoDO.builder()
                .author("sigal")
                .title("study java")
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        mongoTemplate.insert(blogInfoDO);
        System.out.println(blogInfoDO);
    }
}
