package com.sigalhu.jse.curl.es;

import org.junit.Test;

/**
 * @author huxujun
 * @date 2020/2/2
 */
public class IndexTest extends BaseTest {

    @Test
    public void mapping() {
        // 获取映射
        exec("curl -XGET \"http://localhost:9200/get-together/_mapping/group?pretty\"");
    }
}
