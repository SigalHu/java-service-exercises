package com.sigalhu.jse.spring.desserteater;

import com.sigalhu.jse.spring.desserteater.annotation.Cold;
import com.sigalhu.jse.spring.desserteater.annotation.Fruity;
import com.sigalhu.jse.spring.desserteater.config.DessertConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/8/9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DessertConfig.class)
public class DessertTest {

    @Autowired
    private Dessert dessert;

    @Autowired
    //该注解所设置的参数就是想要注入的bean的id，bean的默认id为首字母变为小写的类名
    //更准确地讲，该注解所引用的bean要具有String类型的cookies作为限定符
    //如果没有指定其他的限定符，所有的bean都会给定一个与bean id相同的默认限定符
    @Qualifier("cookies")
    private Dessert dessert2;

    @Autowired
    @Qualifier("sweet")
    private Dessert dessert3;

    @Autowired
    @Qualifier("sweetCake")
    private Dessert dessert4;

    @Autowired
    //在注入点使用必要的限定符注解进行任意组合，从而将可选范围缩小到只有一个bean满足要求
    @Cold
    @Fruity
    private Dessert dessert5;

    @Test
    public void eat() {
        Assert.assertNotNull(dessert);
        Assert.assertNotNull(dessert2);
        Assert.assertNotNull(dessert3);
        Assert.assertNotNull(dessert4);
        Assert.assertNotNull(dessert5);
        dessert.eat();
        dessert2.eat();
        dessert3.eat();
        dessert4.eat();
        dessert5.eat();
    }
}