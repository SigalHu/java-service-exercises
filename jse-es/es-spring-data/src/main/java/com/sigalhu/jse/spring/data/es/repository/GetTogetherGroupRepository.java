package com.sigalhu.jse.spring.data.es.repository;

import com.sigalhu.jse.spring.data.es.beans.GetTogetherGroupDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huxujun
 * @date 2020/1/25
 */
@Repository
public interface GetTogetherGroupRepository extends ElasticsearchRepository<GetTogetherGroupDO, Long> {
}
