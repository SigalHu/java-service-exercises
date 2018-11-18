package com.sigalhu.jse.cglib.beanutility;

import com.sigalhu.jse.cglib.targetobject.Cat;
import com.sigalhu.jse.cglib.targetobject.Dog;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class BeanCopyUtilsTest {

    @Test
    public void copy() throws Exception {
        Dog dog = new Dog("BeanCopyUtilsTest");
        Assert.assertEquals("BeanCopyUtilsTest", dog.getName());
        Cat cat = BeanCopyUtils.copy(dog, Cat.class);
        Assert.assertEquals("BeanCopyUtilsTest", cat.getName());
    }

    @Test
    public void test() throws Exception {
        int count = 10000;
        Dog dog = new Dog("BeanCopyUtilsTest");

        long time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            Cat cat = BeanCopyUtils.copy(dog, Cat.class);
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Using BeanCopier: " + time + "ms");

        time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            Cat cat = new Cat();
            cat.setName(dog.getName());
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Using Setter: " + time + "ms");
    }
}