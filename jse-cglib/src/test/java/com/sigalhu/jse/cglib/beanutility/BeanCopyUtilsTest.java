package com.sigalhu.jse.cglib.beanutility;

import com.sigalhu.jse.cglib.targetobject.CatImpl;
import com.sigalhu.jse.cglib.targetobject.DogImpl;
import org.junit.Assert;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class BeanCopyUtilsTest {

    @Test
    public void copy() throws Exception {
        DogImpl dog = new DogImpl("BeanCopyUtilsTest");
        Assert.assertEquals("BeanCopyUtilsTest", dog.getName());
        CatImpl cat = new CatImpl();
        BeanCopyUtils.copy(dog, cat);
        Assert.assertEquals("BeanCopyUtilsTest", cat.getName());
    }

    @Test
    public void test() throws Exception {
        int count = 10000000;
        DogImpl dog = new DogImpl("BeanCopyUtilsTest");

        long time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            CatImpl cat = new CatImpl();
            BeanCopyUtils.copy(dog, cat);
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Using BeanCopier: " + time + "ms");

        time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            CatImpl cat = new CatImpl();
            BeanCopyUtils.staticCopy(dog, cat);
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Using static BeanCopier: " + time + "ms");

        Field dogName = DogImpl.class.getDeclaredField("name");
        dogName.setAccessible(true);
        Field catName = CatImpl.class.getDeclaredField("name");
        catName.setAccessible(true);
        time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            CatImpl cat = new CatImpl();
            catName.set(cat, dogName.get(dog));
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Using Field: " + time + "ms");

        BeanInfo dogInfo = Introspector.getBeanInfo(DogImpl.class);
        Method dogNameRead = null;
        for (PropertyDescriptor propertyDescriptor : dogInfo.getPropertyDescriptors()) {
            if ("name".equals(propertyDescriptor.getName())) {
                dogNameRead = propertyDescriptor.getReadMethod();
            }
        }
        BeanInfo catInfo = Introspector.getBeanInfo(CatImpl.class);
        Method catNameWrite = null;
        for (PropertyDescriptor propertyDescriptor : catInfo.getPropertyDescriptors()) {
            if ("name".equals(propertyDescriptor.getName())) {
                catNameWrite = propertyDescriptor.getWriteMethod();
            }
        }
        time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            CatImpl cat = new CatImpl();
            catNameWrite.invoke(cat, dogNameRead.invoke(dog));
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Using Method: " + time + "ms");

        time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            CatImpl cat = new CatImpl();
            cat.setName(dog.getName());
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Using Setter: " + time + "ms");
    }
}