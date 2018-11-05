package com.sigalhu.jse.spring.data.mongo;

import com.mongodb.WriteResult;
import com.sigalhu.jse.spring.data.mongo.dataobject.BlogInfoDO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

    @Test
    public void updateFirst() {
        WriteResult writeResult = mongoOperations.updateFirst(
                Query.query(Criteria.where("_id").is("5be08b6b926ee3be0598b8d3")),
                new Update()
                        .set("createTime", new Date().getTime())
//                        .set("lastModifiedBy", "sigal")
                        .set("title", "1111"),
                BlogInfoDO.class);
        Assert.assertTrue(writeResult.isUpdateOfExisting());
    }
}
