package com.sigalhu.jse.lombok.data;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/8/24
 */
public class Example1Test {

    @Test
    public void test() {
        final String name = "Sigal";
        final Integer age = 25;
        final Double score = 100.0;
        final String[] tages = {"1", "2"};

        Example1 ex = new Example1(name, age);
        ex.setScore(score);
        ex.setTags(tages);
        Assert.assertEquals(name, ex.getName());
        Assert.assertEquals(age, age);
        Assert.assertEquals(score, ex.getScore());
        Assert.assertArrayEquals(tages, ex.getTags());
        System.out.println(ex);
    }
}