package com.sigalhu.jse.mockito;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * @author huxujun
 * @date 2018/9/22
 */
public class ArgumentMatchersTest {

    @Test
    @SuppressWarnings("unchecked")
    public void test() {

        /**
         * 参数匹配
         *
         * 一旦某个方法的参数使用了参数匹配，则该方法所有的参数都得使用参数匹配
         */
        //mock creation
        List<String> mockedList = Mockito.mock(List.class);

        //使用內置的参数匹配函数anyInt()
        Mockito.when(mockedList.get(Mockito.anyInt()))
                .thenReturn("element");

        //intThat()方法用来应用自定义的规则，可以传入任何实现Matcher接口的自定义参数匹配器
        Mockito.when(mockedList.get(Mockito.intThat(integer -> integer < 10)))
                .thenReturn("less than 10");

        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(999));

        //也可以通过参数匹配方法进行验证
        Mockito.when(mockedList.set(Mockito.anyInt(), Mockito.anyString())).thenReturn("set 123");

        System.out.println(mockedList.set(1, "123456"));

        //下面的方法会报错，因为第一个参数沒有使用参数匹配，而第二个参数使用了参数匹配
        //Mockito.verify(mockedList).set(1, Mockito.anyString());
        //应改为
        Mockito.verify(mockedList).set(Mockito.eq(1), Mockito.anyString());
    }
}
