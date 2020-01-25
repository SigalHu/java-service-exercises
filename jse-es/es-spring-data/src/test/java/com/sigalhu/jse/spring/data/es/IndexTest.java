package com.sigalhu.jse.spring.data.es;

import com.sigalhu.jse.spring.data.es.beans.GetTogetherGroup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

/**
 * @author huxujun
 * @date 2020/1/25
 */
public class IndexTest extends BaseTest {

    @Before
    public void setUp() throws Exception {
        Assert.assertFalse(elasticsearchTemplate.indexExists(GetTogetherGroup.class));
        Assert.assertTrue(elasticsearchTemplate.createIndex(GetTogetherGroup.class));
    }

    @After
    public void tearDown() throws Exception {
        Assert.assertTrue(elasticsearchTemplate.deleteIndex(GetTogetherGroup.class));
        Assert.assertFalse(elasticsearchTemplate.indexExists(GetTogetherGroup.class));
    }

    @Test
    public void index() {
        Assert.assertTrue(elasticsearchTemplate.indexExists(GetTogetherGroup.class));
    }

    @Test
    public void mapping() {
        Assert.assertTrue(elasticsearchTemplate.putMapping(GetTogetherGroup.class));
        System.out.println(elasticsearchTemplate.getMapping(GetTogetherGroup.class));
    }
}
