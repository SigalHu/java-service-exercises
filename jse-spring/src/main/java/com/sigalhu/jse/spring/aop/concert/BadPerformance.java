package com.sigalhu.jse.spring.aop.concert;

import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/8/14
 */
@Component
public class BadPerformance implements Performance {
    @Override
    public void perform() throws Exception {
        throw new Exception("A bad performance");
    }
}
