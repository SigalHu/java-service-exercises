package com.sigalhu.jse.lombok.nonnull;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/8/22
 */
public class Example1Test {

    @Test
    public void test() {
        final String name = "sigal";
        final Integer age = 25;

        Example1 ex1 = new Example1(name, age);
        Assert.assertEquals(name, ex1.getName());
        Assert.assertEquals(age, ex1.getAge());
    }
}