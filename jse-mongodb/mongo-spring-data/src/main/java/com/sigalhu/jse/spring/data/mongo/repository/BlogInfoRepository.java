package com.sigalhu.jse.spring.data.mongo.repository;

import com.sigalhu.jse.spring.data.mongo.dataobject.BlogInfoDO;
import com.sigalhu.jse.spring.data.mongo.repository.operations.BlogInfoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * 任何扩展MongoRepository的接口将会在运行时自动生成实现，MongoRepository接口有两个参数
 * 第一个是带有@Document注解的对象类型，第二个参数是带有@Id注解的属性类型
 *
 * @author huxujun
 * @date 2018/11/3
 */
public interface BlogInfoRepository extends MongoRepository<BlogInfoDO, String>, BlogInfoOperations {

    @Query("{nickname:?0}")
    List<BlogInfoDO> findByAuthor(String author);
}
