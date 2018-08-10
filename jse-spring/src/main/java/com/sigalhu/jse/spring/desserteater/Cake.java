package com.sigalhu.jse.spring.desserteater;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/8/9
 */
@Component("sweetCake")
//通过该注解，我们可以为bean设置自己的限定符，而不是依赖于将bean id作为限定符
@Qualifier("sweet")
public class Cake implements Dessert {

    @Override
    public void eat() {
        System.out.println("Eating Cake");
    }
}
