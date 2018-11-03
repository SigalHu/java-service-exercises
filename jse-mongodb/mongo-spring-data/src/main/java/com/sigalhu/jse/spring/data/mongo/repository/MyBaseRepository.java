package com.sigalhu.jse.spring.data.mongo.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author huxujun
 * @date 2018/11/4
 */
@NoRepositoryBean
public interface MyBaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    Optional<T> findById(ID id);

    <S extends T> S save(S entity);

    <S extends T> S insert(S entity);
}
