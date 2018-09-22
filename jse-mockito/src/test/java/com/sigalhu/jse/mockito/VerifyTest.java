package com.sigalhu.jse.mockito;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * 一旦一个mock对象被创建，那么该对象所有交互行为都会被记住。
 * 比如下面例子中它可以记住mockedList某个方法是否被调用过或者被调用过几次。
 *
 * @author huxujun
 * @date 2018/9/22
 */
public class VerifyTest {

    @Test
    @SuppressWarnings("unchecked")
    public void test() {
        //mock creation
        List mockedList = Mockito.mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        //验证add方法是否在前面被调用了一次，且参数为“one”，clear方法同样
        Mockito.verify(mockedList).add("one");
        Mockito.verify(mockedList).clear();

        //下面的验证会失败，因为没有调用过add("two")
        Mockito.verify(mockedList).add("two");

        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //下面两种写法都是针对调用1次，因为1是默认的，我们一般使用第一种写法就可以
        Mockito.verify(mockedList).add("once");
        Mockito.verify(mockedList, Mockito.times(1)).add("once");

        //指定了具体调用的次数
        Mockito.verify(mockedList, Mockito.times(2)).add("twice");
        Mockito.verify(mockedList, Mockito.times(3)).add("three times");

        //使用了never()，即times(0)
        Mockito.verify(mockedList, Mockito.never()).add("never happened");

        //使用了最多多少次，最少多少次
        Mockito.verify(mockedList, Mockito.atLeastOnce()).add("three times");
        Mockito.verify(mockedList, Mockito.atLeast(2)).add("three times");
        Mockito.verify(mockedList, Mockito.atMost(5)).add("three times");
    }
}
