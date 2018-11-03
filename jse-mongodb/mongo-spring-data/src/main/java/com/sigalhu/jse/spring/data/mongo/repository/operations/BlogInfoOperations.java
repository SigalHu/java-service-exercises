package com.sigalhu.jse.spring.data.mongo.repository.operations;

import com.sigalhu.jse.spring.data.mongo.dataobject.BlogInfoDO;

import java.util.List;

/**
 * @author huxujun
 * @date 2018/11/3
 */
public interface BlogInfoOperations {

    List<BlogInfoDO> findByTitleAndAuthor(String title, String author);
}
