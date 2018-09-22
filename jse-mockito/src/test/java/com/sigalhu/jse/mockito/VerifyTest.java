package com.sigalhu.jse.mockito;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

/**
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
        //验证add方法是否在前面被调用了一次，且参数为“one”。clear方法同样
        Mockito.verify(mockedList).add("one");
        Mockito.verify(mockedList).clear();

        //下面的验证会失败。因为没有调用过add("two")
        Mockito.verify(mockedList).add("two");
    }
}
