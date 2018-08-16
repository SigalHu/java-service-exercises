package com.sigalhu.jse.spring.aop.concert;

/**
 * @author huxujun
 * @date 2018/8/16
 */
public class DefaultEncoreable implements Encoreable {
    @Override
    public void performEncore() {
        System.out.println("Perform again");
    }
}
