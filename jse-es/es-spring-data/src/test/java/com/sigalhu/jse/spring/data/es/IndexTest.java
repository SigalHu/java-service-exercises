package com.sigalhu.jse.spring.data.es;

import com.sigalhu.jse.spring.data.es.beans.GetTogetherGroupDO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2020/1/25
 */
public class IndexTest extends BaseTest {

    @Before
    public void setUp() throws Exception {
        Assert.assertFalse(elasticsearchTemplate.indexExists(GetTogetherGroupDO.class));
        Assert.assertTrue(elasticsearchTemplate.createIndex(GetTogetherGroupDO.class));
    }

    @After
    public void tearDown() throws Exception {
        Assert.assertTrue(elasticsearchTemplate.deleteIndex(GetTogetherGroupDO.class));
        Assert.assertFalse(elasticsearchTemplate.indexExists(GetTogetherGroupDO.class));
    }

    @Test
    public void index() {
        Assert.assertTrue(elasticsearchTemplate.indexExists(GetTogetherGroupDO.class));
    }

    @Test
    public void mapping() {
        Assert.assertTrue(elasticsearchTemplate.putMapping(GetTogetherGroupDO.class));
        System.out.println(elasticsearchTemplate.getMapping(GetTogetherGroupDO.class));
    }
}
