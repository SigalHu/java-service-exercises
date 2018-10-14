package com.sigalhu.jse.lombok.getter;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/8/23
 */
public class Example1Test {

    @Test
    public void test() {
        final int id = 1;
        final boolean old = false;
        final boolean male = true;
        final String name = "sigal";
        final int age = 26;
        final String[] tags = {"1", "2"};

        Example1.setId(id);
        Example1 ex1 = new Example1();
        ex1.setName(name);
        ex1.setOld(old);
        ex1.setMale(male);

        Assert.assertEquals(id, Example1.getId());
        Assert.assertFalse(ex1.isOld());
        Assert.assertTrue(ex1.isMale());
        Assert.assertEquals(name, ex1.getName());

        System.out.println("start call:");
        Assert.assertArrayEquals(tags, ex1.getTags());
        System.out.println("end call!");
    }
}