package com.sigalhu.jse.spring.aop.concert;

import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/8/14
 */
@Component
public class GoodPerformance implements Performance {
    @Override
    public void perform() {
        System.out.println("A good performance");
    }
}
