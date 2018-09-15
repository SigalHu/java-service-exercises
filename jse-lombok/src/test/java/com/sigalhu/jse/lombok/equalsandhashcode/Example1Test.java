package com.sigalhu.jse.lombok.equalsandhashcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/8/24
 */
public class Example1Test {

    @Test
    public void test() {
        final int[] id = {1, 2};
        final String[] name = {"Sigal1", "Sigal2"};
        final int[] age = {25, 26};
        final String[] sex = {"male", "female"};
        final int[] width = {10, 20};
        final int[] height = {30, 40};

        Example1 ex1 = new Example1();
        ex1.setId(id[0]);
        ex1.setAge(age[0]);
        ex1.setSex(sex[0]);
        ex1.setWidth(width[0]);
        ex1.setHeight(height[0]);

        Example1 ex2 = new Example1();
        ex2.setId(id[0]);
        ex2.setAge(age[0]);
        ex2.setSex(sex[1]);
        ex2.setWidth(width[0]);
        ex2.setHeight(height[1]);

        Assert.assertEquals(ex1, ex2);

        ex2.setId(id[1]);
        Assert.assertNotEquals(ex1, ex2);
    }
}