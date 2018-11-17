package com.sigalhu.jse.cglib.lazyloader;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class DogLazyLoaderTest {

    @Test
    public void dogBark() {
        System.out.println("Start DogLazyLoader DogLazyLoader()");
        DogLazyLoader dogLazyLoader = new DogLazyLoader();
        System.out.println("End DogLazyLoader DogLazyLoader()");
        System.out.println();
        System.out.println("Start DogLazyLoader dogBark()");
        dogLazyLoader.dogBark();
        System.out.println("End DogLazyLoader dogBark()");
        System.out.println();
        System.out.println("Start DogLazyLoader dogBark()");
        String bark = dogLazyLoader.dogBark();
        System.out.println("End DogLazyLoader dogBark()");

        Assert.assertEquals("Dog bark: wow wow wow! --- DogLazyLoader", bark);
    }
}