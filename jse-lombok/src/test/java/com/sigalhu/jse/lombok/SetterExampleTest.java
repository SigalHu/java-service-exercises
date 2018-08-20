package com.sigalhu.jse.lombok;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/8/21
 */
public class SetterExampleTest {

    @Test
    public void test() {
        final String name = "sigal";
        final String mob = "1234567";
        final String sex = "male";

        SetterExample ex = new SetterExample();
        ex.setName(name);
        ex.setMob(mob);
        ex.setSex(sex);
        Assert.assertNotNull(ex);
        Assert.assertEquals(name, ex.getName());
        Assert.assertEquals(mob, ex.getMob());
        Assert.assertEquals(sex, ex.getSex());
    }
}