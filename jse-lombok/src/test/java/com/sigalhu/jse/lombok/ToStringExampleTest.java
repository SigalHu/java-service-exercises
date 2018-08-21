package com.sigalhu.jse.lombok;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/8/22
 */
public class ToStringExampleTest {

    @Test
    public void test() {
        final String name = "sigal";
        final String mob = "1234567";
        final String sex = "male";
        final String expectedStr = "ToStringExample(name=" + name + ", mob=" + mob + ", sex=" + sex + ")";

        ToStringExample ex = new ToStringExample();
        ex.setName(name);
        ex.setMob(mob);
        ex.setSex(sex);
        Assert.assertNotNull(ex);
        Assert.assertEquals(expectedStr, ex.toString());
    }
}