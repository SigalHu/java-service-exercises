package com.sigalhu.jse.spring.data.mongo;

import com.mongodb.WriteResult;
import com.sigalhu.jse.spring.data.mongo.dataobject.BlogInfoDO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.Date;

/**
 * @author huxujun
 * @date 2018/11/3
 */
public class MongoOperationsTest extends BaseTest {

    @Autowired
    private MongoOperations mongoOperations;

    private BlogInfoDO blogInfoDO;

    @Before
    public void init() {
        blogInfoDO = BlogInfoDO.builder()
                .author("sigal")
                .title("study java")
                .name("test")
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }

    @Test
    public void insert() {
        mongoOperations.insert(blogInfoDO);
        System.out.println(blogInfoDO);
    }

    @Test
    public void save() {
        mongoOperations.save(blogInfoDO);
        System.out.println(blogInfoDO);
    }

    @Test
    public void remove() {
        blogInfoDO.setId("5bdc9e6c926e05416d9bd09d");
        WriteResult writeResult = mongoOperations.remove(blogInfoDO);
        System.out.println(writeResult);
    }

    @Test
    public void count() {
        System.out.println(mongoOperations.count(null, BlogInfoDO.class));
    }
}
