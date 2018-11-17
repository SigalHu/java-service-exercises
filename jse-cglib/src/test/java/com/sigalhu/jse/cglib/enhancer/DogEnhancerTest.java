package com.sigalhu.jse.cglib.enhancer;

import com.sigalhu.jse.cglib.targetobject.Dog;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/11/16
 */
public class DogEnhancerTest {

    @Test
    public void test() {
        Dog proxyDog = DogEnhancer.create();
        Assert.assertNotEquals(Dog.class, proxyDog.getClass());
        Assert.assertEquals("Hello cglib!", proxyDog.toString());
        Assert.assertEquals("[cglib] Dog bark: wow wow wow! --- DogEnhancer", proxyDog.bark());
    }
}