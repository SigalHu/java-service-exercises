package com.sigalhu.jse.spring.data.mongo;

import com.sigalhu.jse.spring.data.mongo.dataobject.BlogInfoDO;
import com.sigalhu.jse.spring.data.mongo.repository.BlogInfoRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
                .build();
    }

    @Test
    public void insert() {
        blogInfoRepository.insert(blogInfoDO);
        System.out.println(blogInfoDO);
    }

    @Test
    public void findByAuthor() {
        String author = "sigal";
        Pageable page = new PageRequest(0, 2);

        Integer count = blogInfoRepository.countByAuthor(author);
        System.out.println("count=" + count);
        List<BlogInfoDO> blogInfoDOS = blogInfoRepository.findByAuthor(author, page);
        System.out.println(blogInfoDOS);
    }

    @Test
    public void findByTitleAndAuthor() {
        List<BlogInfoDO> blogInfoDOS = blogInfoRepository.findByTitleAndAuthor(null, "sigal");
        System.out.println(blogInfoDOS);
    }

    @Test
    public void findTop2ByCreateTimeBetweenAndUpdateTimeBetween() {
        LocalDateTime localDateTime = LocalDateTime.parse("2018-11-03T00:00:00");
        List<BlogInfoDO> blogInfoDOS = blogInfoRepository.findTop2ByCreateTimeBetweenAndUpdateTimeBetween(
                Date.from(localDateTime.toInstant(ZoneOffset.UTC)),
                Date.from(localDateTime.plusDays(1).toInstant(ZoneOffset.UTC)),
                Date.from(localDateTime.toInstant(ZoneOffset.UTC)),
                Date.from(localDateTime.plusDays(1).toInstant(ZoneOffset.UTC)));
        System.out.println(blogInfoDOS);
    }

    @Test
    public void deleteByAuthor() {
        String author = "sigal";
        Long count = blogInfoRepository.deleteByAuthor(author);
        System.out.println(count);
    }
}
