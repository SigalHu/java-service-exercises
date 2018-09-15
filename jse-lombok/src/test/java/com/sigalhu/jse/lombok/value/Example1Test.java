package com.sigalhu.jse.lombok.value;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/8/25
 */
public class Example1Test {

    @Test
    public void test() {
        final String name = "Sigal";
        final int age = 25;
        final double score = 100;
        final String[] tags = {"1", "2"};

        Example1 ex = new Example1(name, age, score, tags);
        Assert.assertEquals(name, ex.getName());
        Assert.assertEquals(age, ex.getAge());
        Assert.assertEquals(score, ex.getScore(), 1e-10);
        Assert.assertArrayEquals(tags, ex.getTags());
        Assert.assertArrayEquals(tags, ex.tags);
        Assert.assertNotEquals(ex, ex.withAge(26));
    }

}