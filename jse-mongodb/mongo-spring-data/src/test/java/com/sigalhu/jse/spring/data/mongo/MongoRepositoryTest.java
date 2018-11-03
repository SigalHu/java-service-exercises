package com.sigalhu.jse.spring.data.mongo;

import com.sigalhu.jse.spring.data.mongo.dataobject.BlogInfoDO;
import com.sigalhu.jse.spring.data.mongo.repository.BlogInfoRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author huxujun
 * @date 2018/11/3
 */
public class MongoRepositoryTest extends BaseTest {

    @Autowired
    private BlogInfoRepository blogInfoRepository;

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
        blogInfoRepository.insert(blogInfoDO);
        System.out.println(blogInfoDO);
    }

    @Test
    public void findByAuthor() {
        List<BlogInfoDO> blogInfoDOS = blogInfoRepository.findByAuthor("sigal");
        System.out.println(blogInfoDOS);
    }

    @Test
    public void findByTitleAndAuthor() {
        List<BlogInfoDO> blogInfoDOS = blogInfoRepository.findByTitleAndAuthor(null, "sigal");
        System.out.println(blogInfoDOS);
    }
}
