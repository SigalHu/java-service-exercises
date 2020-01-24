package com.sigalhu.jse.spring.data.es;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2020/1/25
 */
public class IndexTest extends BaseTest {

    @Test
    public void indexOperation() {
        final String indexName = "test";
        Assert.assertFalse(elasticsearchTemplate.indexExists(indexName));

        Assert.assertTrue(elasticsearchTemplate.createIndex(indexName));
        Assert.assertTrue(elasticsearchTemplate.indexExists(indexName));
        Assert.assertTrue(elasticsearchTemplate.deleteIndex(indexName));

        Assert.assertFalse(elasticsearchTemplate.indexExists(indexName));
    }
}
