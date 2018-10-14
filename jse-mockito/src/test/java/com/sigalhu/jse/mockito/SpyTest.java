package com.sigalhu.jse.mockito;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

/**
 * @author huxujun
 * @date 2018/9/22
 */
public class SpyTest {

    /**
     * 监控一个真实的对象
     *
     * Mock对象的方法是不会被真实调用的，Spy则不同，它是真实的去调用那个方法，
     * 但是你又可以在需要时候对某些方法设置期望的返回值，如果没有设置的话则会真实去调用那个方法
     */
    @Test
    public void spyingOnRealObjects() {
        List<String> list = new LinkedList<>();
        List<String> spy = Mockito.spy(list);

        // Spy对象也可以设置期望值
        Mockito.when(spy.size()).thenReturn(100);

        // 使用spy对象调用真实的方法
        spy.add("one");
        spy.add("two");

        // 输出"one"
        System.out.println(spy.get(0));

        // 由于我们对size设置了期望值，所以下面会输出期望值100
        System.out.println(spy.size());

        // 验证方法的调用
        Mockito.verify(spy).add("one");
        Mockito.verify(spy).add("two");
    }
}
