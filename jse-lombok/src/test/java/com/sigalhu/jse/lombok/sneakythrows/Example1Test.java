package com.sigalhu.jse.lombok.sneakythrows;

import org.junit.Test;

import java.io.IOException;

/**
 * @author huxujun
 * @date 2018/9/3
 */
public class Example1Test {

    @Test(expected = IOException.class)
    public void test() {
        Example1 ex = new Example1();
        ex.throwIOException();
    }
}