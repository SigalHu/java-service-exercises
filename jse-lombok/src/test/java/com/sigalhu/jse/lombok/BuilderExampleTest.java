package com.sigalhu.jse.lombok;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/8/21
 */
public class BuilderExampleTest {

    @Test
    public void test() {
        final String name = "sigal";
        final String mob = "1234567";
        final String sex = "male";

        BuilderExample ex1 = BuilderExample.builder()
                .name(name)
                .mob(mob)
                .sex(sex)
                .build();
        Assert.assertNotNull(ex1);
        Assert.assertEquals(name, ex1.getName());
        Assert.assertEquals(mob, ex1.getMob());
        Assert.assertEquals(sex, ex1.getSex());

        //builder注解会生成package访问权限的构造函数，因此会隐藏无参构造函数
        BuilderExample ex2 = new BuilderExample(name, mob, sex);
        Assert.assertNotNull(ex2);
        Assert.assertEquals(name, ex2.getName());
        Assert.assertEquals(mob, ex2.getMob());
        Assert.assertEquals(sex, ex2.getSex());
    }
}