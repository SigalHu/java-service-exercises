package com.sigalhu.jse.spring.aop.soundsystem;

/**
 * @author huxujun
 * @date 2018/8/16
 */
public class DefaultEncoreable implements Encoreable {
    @Override
    public void playEncore() {
        System.out.println("Play it again");
    }
}
