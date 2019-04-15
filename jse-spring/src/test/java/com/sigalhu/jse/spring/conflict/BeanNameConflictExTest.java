package com.sigalhu.jse.spring.conflict;

import com.sigalhu.jse.spring.conflict.bean1.BeanNameConflictExImpl;
import com.sigalhu.jse.spring.conflict.config.ConflictConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author huxujun
 * @date 2018/9/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConflictConfig.class)
public class BeanNameConflictExTest {


    // @Autowired默认按类型装配，默认情况下必须要求依赖对象必须存在，如果要允许null值，
    // 可以设置它的required属性为false，如：@Autowired(required=false) ，
    // 如果我们想使用名称装配可以结合@Qualifier注解进行使用
    @Autowired
    private BeanNameConflictExImpl beanNameConflictExImpl;

    // @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按byName自动注入罢了。
    // @Resource有两个属性是比较重要的，分别是name和type，Spring将@Resource注解的name属性解析为bean的名字，
    // 而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。
    // 如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。

    // @Resource装配顺序
    // 1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常
    // 2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常
    // 3. 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常
    // 4. 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配；

    // 因此若@Resource注解的字段名为beanNameConflictExImpl会报错，而@Autowired注解的字段不会
    @Resource
    private BeanNameConflictExImpl beanNameConflictExImpl2;

    // 在这里，@Resource使用byName注入成功，@Autowired先使用byType，发现有两个实现了该接口的bean，在此基础上使用byName注入成功
    @Resource
    @Autowired
    private BeanNameConflictEx beanName1;

    @Test
    public void hello() {
        beanNameConflictExImpl.hello();
        beanNameConflictExImpl2.hello();
        beanName1.hello();
    }
}
