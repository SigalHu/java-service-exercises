package com.sigalhu.jse.curl.es;

import org.junit.Test;

/**
 * @author huxujun
 * @date 2020/2/2
 */
public class CurdTest extends BaseTest {

    @Test
    public void put() {
        // 索引文档
        exec(
                "curl -XPUT \"http://localhost:9200/get-together/group/1?pretty\" -H 'Content-Type: application/json' -d'\n" +
                        "{\n" +
                        "  \"name\": \"Elasticsearch Denver\",\n" +
                        "  \"organizer\": \"Lee\"\n" +
                        "}'"
        );
    }
}
