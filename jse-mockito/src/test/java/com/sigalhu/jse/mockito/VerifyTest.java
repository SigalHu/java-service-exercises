package com.sigalhu.jse.mockito;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.List;

/**
 * @author huxujun
 * @date 2018/9/22
 */
@SuppressWarnings("unchecked")
public class VerifyTest {

    /**
     * 验证行为
     *
     * 一旦一个mock对象被创建，那么该对象所有交互行为都会被记住。
     * 比如下面例子中它可以记住mockedList某个方法是否被调用过或者被调用过几次。
     */
    @Test
    public void verifyBehaviour() {
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
    }

    /**
     * 验证调用的具体次数/最少次数/从未调用
     *
     * 默认调用一次，所以对于1次我们可以省略掉times(1)
     */
    @Test
    public void verifyingInvocationsNumber() {
        //mock creation
        List mockedList = Mockito.mock(List.class);

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

    /**
     * 验证调用顺序
     *
     * Mockito对于顺序的验证是比较灵活的，你不必一一验证所有的调用，只需要验证你所需要的即可
     */
    @Test
    public void verificationInOrder() {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = Mockito.mock(List.class);

        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = Mockito.inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = Mockito.mock(List.class);
        List secondMock = Mockito.mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //创建InOrder对象时只需要传入你所需要验证顺序的Mock对象即可
        InOrder inOrder2 = Mockito.inOrder(firstMock, secondMock);

        //下面这两个是正确的，调用顺序正确
        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");

        //下面这两个会失败，因为调用的顺序出错了
        inOrder.verify(secondMock).add("was called second");
        inOrder.verify(firstMock).add("was called first");
    }

    /**
     * 确保Mock对象从未调用过
     */
    @Test
    public void interactionOnMock() {
        List mockOne = Mockito.mock(List.class);
        List mockTwo = Mockito.mock(List.class);
        List mockThree = Mockito.mock(List.class);

        //mockOne和mockTwo都有调用，mockThree没有
        mockOne.add("one");
        mockTwo.add("two");

        //普通的调用验证
        Mockito.verify(mockOne).add("one");

        //add("two")从未被调用过
        Mockito.verify(mockOne, Mockito.never()).add("two");

        //下面的验证会失败，因为mockOne和mockTwo都有调用
        Mockito.verifyZeroInteractions(mockOne, mockTwo);

        //下面验证会成功，因为mockThree没有过调用
        Mockito.verifyZeroInteractions(mockOne, mockThree);
    }

    /**
     * 寻找冗余的调用
     *
     * 不要滥用verifyNoMoreInteractions()方法，只有当需要用的时候再用，滥用会导致不好维护
     */
    @Test
    public void findRedundantInvocations() {
        //mock creation
        List mockOne = Mockito.mock(List.class);
        List mockTwo = Mockito.mock(List.class);

        //using mocks
        mockOne.add("one");
        mockOne.add("two");

        mockTwo.add("one");

        Mockito.verify(mockOne).add("one");

        Mockito.verify(mockTwo).add("one");

        //下面验证会失败
        Mockito.verifyNoMoreInteractions(mockOne);

        //下面验证会成功
        Mockito.verifyNoMoreInteractions(mockTwo);
    }
}
