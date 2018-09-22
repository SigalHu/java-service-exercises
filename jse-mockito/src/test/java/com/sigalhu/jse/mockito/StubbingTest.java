package com.sigalhu.jse.mockito;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;

/**
 * @author huxujun
 * @date 2018/9/22
 */
public class StubbingTest {

    /**
     * 返回期望值
     *
     * 对于有返回值但没有设置期望值的模拟对象，Mockito会返回相应的默认值，
     * 内置类型int会返回0，boolean返回false，其他则返回null。
     * 这个返回默认值主要是因为Mock对象会覆盖（override）整个被Mock的对象的方法，
     * 所以没有设置期望值的就只能返回默认值了。
     */
    @Test
    public void stubbing() {
        //我们不仅可以模拟接口还可以模拟具体类
        LinkedList mockedList = Mockito.mock(LinkedList.class);

        //stubbing
        //当get(0)被调用时，返回"first". 方法get(1)被调用时，抛异常
        Mockito.when(mockedList.get(0)).thenReturn("first");
        Mockito.when(mockedList.get(1)).thenThrow(new RuntimeException());

        //下面会输出"first"
        System.out.println(mockedList.get(0));

        //下面会输出"null"，因为999没有被设置期望值
        System.out.println(mockedList.get(999));

        //重复stub两次,则以第二次为准。如下将返回"second"：
        Mockito.when(mockedList.get(0)).thenReturn("first");
        Mockito.when(mockedList.get(0)).thenReturn("second");
        // 输出"second"
        System.out.println(mockedList.get(0));

        //如果是下面这种形式，则表示第一次调用时返回“first”，第二次调用时返回“second”。可以写n多个。
        Mockito.when(mockedList.get(0)).thenReturn("first").thenReturn("second");
        // 输出"first"
        System.out.println(mockedList.get(0));
        // 输出"second"
        System.out.println(mockedList.get(0));
        // 输出"second"
        System.out.println(mockedList.get(0));

        //下面会抛出异常，因为设置的返回值是异常
        System.out.println(mockedList.get(1));
    }

    /**
     * 迭代调用
     *
     * 有时我们可以需要调用同一个方法多次，每次需要有不同的返回值（例如迭代），这时我们需要
     * 对同一个方法设置不同的返回值/期望值
     */
    @Test
    public void stubbingConsecutiveCalls() {
        LinkedList mock = Mockito.mock(LinkedList.class);

        Mockito.when(mock.get(0))
                .thenReturn("first")
                .thenReturn("second")
                .thenReturn("third");

        // 第一次调用，输出"first"
        System.out.println(mock.get(0));

        // 第二次调用，输出"second"
        System.out.println(mock.get(0));

        // 第三次及以后调用，都输出"third"
        System.out.println(mock.get(0));
    }
}
