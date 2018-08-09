package com.sigalhu.jse.spring.desserteater;

import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/8/9
 */
@Component
public class Cookies implements Dessert {

    @Override
    public void eat() {
        System.out.println("Eating Cookies");
    }
}
