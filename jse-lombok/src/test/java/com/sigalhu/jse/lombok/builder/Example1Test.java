package com.sigalhu.jse.lombok.builder;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author huxujun
 * @date 2018/8/26
 */
public class Example1Test {

    @Test
    public void test() {
        final long created = System.currentTimeMillis();
        final String name = "Sigal";
        final int age = 26;
        final Set<String> occupations = new HashSet<>();
        occupations.add("1");
        occupations.add("2");

        Example1 ex = Example1.builder()
                .name(name)
                .age(age)
                .occupations(occupations)
                .occupation("3")
                .occupation("4")
                .build();
        occupations.add("3");
        occupations.add("4");

        Assert.assertNotEquals(created, ex.getCreated());
        Assert.assertEquals(name, ex.getName());
        Assert.assertEquals(age, ex.getAge());
        Assert.assertArrayEquals(occupations.toArray(), ex.getOccupations().toArray());
    }
}