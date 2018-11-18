package com.sigalhu.jse.cglib.lazyloader;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class DogProxyRefDispatcherTest {

    @Test
    public void dogBark() {
        System.out.println("Start DogProxyRefDispatcher DogDispatcher()");
        DogProxyRefDispatcher dogProxyRefDispatcher = new DogProxyRefDispatcher();
        System.out.println("End DogProxyRefDispatcher DogDispatcher()");
        System.out.println();
        System.out.println("Start DogProxyRefDispatcher dogBark()");
        dogProxyRefDispatcher.dogBark();
        System.out.println("End DogProxyRefDispatcher dogBark()");
        System.out.println();
        System.out.println("Start DogProxyRefDispatcher dogBark()");
        String bark = dogProxyRefDispatcher.dogBark();
        System.out.println("End DogProxyRefDispatcher dogBark()");

        Assert.assertEquals("Dog bark: wow wow wow! --- DogProxyRefDispatcher", bark);
    }
}