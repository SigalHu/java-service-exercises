package com.sigalhu.jse.lombok.var;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/8/22
 */
public class Example1Test {

    @Test
    public void example() {
        Example1 ex1 = new Example1();
        Assert.assertEquals("hello, java!", ex1.example());
    }
}