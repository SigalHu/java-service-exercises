package com.sigalhu.jse.cglib.beanutility;

import com.sigalhu.jse.cglib.targetobject.Dog;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class BeanMapUtilsTest {

    @Test
    public void convertToMap() {
        Dog dog = new Dog("BeanMapUtilsTest");
        Map<String, Object> map = BeanMapUtils.convertToMap(dog);
        Assert.assertEquals("BeanMapUtilsTest", map.get("name"));

        map.put("name", "BeanMapUtilsTest2");
        Assert.assertEquals("BeanMapUtilsTest2", dog.getName());

        map.put("dogName", "BeanMapUtilsTest3");
        Assert.assertEquals("BeanMapUtilsTest3", dog.getName());
    }
}