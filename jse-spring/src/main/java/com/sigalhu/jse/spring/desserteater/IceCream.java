package com.sigalhu.jse.spring.desserteater;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/8/9
 */
@Component
//使用该注解能够避免自动装配时的歧义性，当遇到歧义性时，Spring将会使用首选bean
//该注解能够与@Component组合用在组件扫描的bean上，也可以用在xml配置或与@Bean组合的Java配置的bean声明中
@Primary
public class IceCream implements Dessert {

    @Override
    public void eat() {
        System.out.println("Eating IceCream");
    }
}
