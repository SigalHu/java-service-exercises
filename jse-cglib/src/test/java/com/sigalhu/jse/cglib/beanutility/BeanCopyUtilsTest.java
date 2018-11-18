package com.sigalhu.jse.cglib.beanutility;

import com.sigalhu.jse.cglib.targetobject.CatImpl;
import com.sigalhu.jse.cglib.targetobject.DogImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class BeanCopyUtilsTest {

    @Test
    public void copy() throws Exception {
        DogImpl dog = new DogImpl("BeanCopyUtilsTest");
        Assert.assertEquals("BeanCopyUtilsTest", dog.getName());
        CatImpl cat = BeanCopyUtils.copy(dog, CatImpl.class);
        Assert.assertEquals("BeanCopyUtilsTest", cat.getName());
    }

    @Test
    public void test() throws Exception {
        int count = 10000;
        DogImpl dog = new DogImpl("BeanCopyUtilsTest");

        long time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            CatImpl cat = BeanCopyUtils.copy(dog, CatImpl.class);
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Using BeanCopier: " + time + "ms");

        time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            CatImpl cat = new CatImpl();
            cat.setName(dog.getName());
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Using Setter: " + time + "ms");
    }
}