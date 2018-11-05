package com.sigalhu.jse.spring.data.mongo;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * 要在Spring data mongodb中使用@CreatedBy/@LastModifiedBy/@CreatedDate/@LastModifiedDate这四个注解必须实现AuditorAware
 *
 * @author huxujun
 * @date 2018/11/6
 */
@Component
public class DefaultAuditorAware implements AuditorAware<Object> {

    @Override
    public Object getCurrentAuditor() {
        return "AAAAAA";
    }
}
