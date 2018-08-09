package com.sigalhu.jse.spring.desserteater;

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
    @Qualifier("cookies")
    private Dessert anotherDessert;

    @Test
    public void eat() {
        Assert.assertNotNull(dessert);
        Assert.assertNotNull(anotherDessert);
        dessert.eat();
        anotherDessert.eat();
    }
}