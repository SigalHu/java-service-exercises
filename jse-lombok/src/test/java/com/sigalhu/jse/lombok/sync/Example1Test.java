package com.sigalhu.jse.lombok.sync;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/9/4
 */
public class Example1Test {

    @Test
    public void test() throws Exception {
        new Thread(()->{
            try {
                Example1.hello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                Example1 ex = new Example1();
                ex.world();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Example1 ex = new Example1();
        ex.foo();
        Thread.sleep(3000);
    }
}