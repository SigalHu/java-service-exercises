package com.sigalhu.exercises.maven.helloworld;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelloWorldTest {

    @Test
    public void testSayHello() {
        HelloWorld helloWorld = new HelloWorld();
        String result = helloWorld.sayHello();
        Assert.assertEquals("Hello Maven", result);
    }
}