package com.sigalhu.jse.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author huxujun
 * @date 2018/9/22
 */
@RunWith(MockitoJUnitRunner.class)
public class AnnotationsTest {

    @InjectMocks
    private Person person;

    /**
     * 快速创建mock对象使用注解@Mock
     *
     * 注解是创建对象的一种快速的方式，在这里我们要注意如果要使用注解则需要测试类里面初始化注解：
     * MockitoAnnotations.initMocks(testClass); 或者 @RunWith(MockitoJUnitRunner.class)
     */
    @Mock
    private Kevin kevin;

//    @Before
//    public void initMocks() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void test() {
        Mockito.when(kevin.say()).thenReturn("I am mock kevin.");
        person.sayAll();
    }
}
