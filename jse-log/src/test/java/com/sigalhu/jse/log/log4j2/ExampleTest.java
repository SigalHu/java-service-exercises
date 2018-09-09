package com.sigalhu.jse.log.log4j2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/9/10
 */
public class ExampleTest {

    @Test
    public void test() {
        Example ex = new Example();
        ex.info("I am info.");
        ex.error("I am error.");
    }
}