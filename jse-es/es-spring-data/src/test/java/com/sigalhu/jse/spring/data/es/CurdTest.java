package com.sigalhu.jse.spring.data.es;

import com.sigalhu.jse.spring.data.es.beans.GetTogetherGroupDO;
import com.sigalhu.jse.spring.data.es.repository.GetTogetherGroupRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huxujun
 * @date 2020/1/25
 */
public class CurdTest extends BaseTest {

    @Autowired
    private GetTogetherGroupRepository repository;

    @Test
    public void test() {
        GetTogetherGroupDO groupDO = new GetTogetherGroupDO();
        groupDO.setName("Elasticsearch Denver");
        groupDO.setOrganizer("Lee");

        repository.save(groupDO);
        System.out.println(elasticsearchTemplate.getMapping(GetTogetherGroupDO.class));
        repository.findAll().forEach(System.out::println);
    }
}
