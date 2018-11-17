package com.sigalhu.jse.cglib.lazyloader;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class DogDispatcherTest {

    @Test
    public void dogBark() {
        System.out.println("Start DogDispatcher DogDispatcher()");
        DogDispatcher dogDispatcher = new DogDispatcher();
        System.out.println("End DogDispatcher DogDispatcher()");
        System.out.println();
        System.out.println("Start DogDispatcher dogBark()");
        dogDispatcher.dogBark();
        System.out.println("End DogDispatcher dogBark()");
        System.out.println();
        System.out.println("Start DogDispatcher dogBark()");
        String bark = dogDispatcher.dogBark();
        System.out.println("End DogDispatcher dogBark()");

        Assert.assertEquals("Dog bark: wow wow wow! --- DogDispatcher", bark);
    }
}