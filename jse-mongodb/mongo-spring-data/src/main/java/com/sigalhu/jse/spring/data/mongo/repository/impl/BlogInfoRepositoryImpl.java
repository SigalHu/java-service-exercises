package com.sigalhu.jse.spring.data.mongo.repository.impl;

import com.sigalhu.jse.spring.data.mongo.dataobject.BlogInfoDO;
import com.sigalhu.jse.spring.data.mongo.repository.operations.BlogInfoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 实现类名称为BlogInfoRepositoryImpl，这个名字前半部分与BlogInfoRepository相同，只是添加了Impl后缀
 * 当Spring Data MongDB生成Repository实现时，它会查找这个类并将其混合到自动生成的实现中
 *
 * @author huxujun
 * @date 2018/11/3
 */
public class BlogInfoRepositoryImpl implements BlogInfoOperations {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<BlogInfoDO> findByTitleAndAuthor(String title, String author) {
        Criteria criteria = new Criteria();
        if (StringUtils.hasText(title)) {
            criteria.and("title").is(title);
        }
        if (StringUtils.hasText(author)) {
            criteria.and("nickname").is(author);
        }
        return mongoOperations.find(Query.query(criteria), BlogInfoDO.class);
    }
}
