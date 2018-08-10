package com.sigalhu.jse.spring.desserteater;

import com.sigalhu.jse.spring.desserteater.annotation.Cold;
import com.sigalhu.jse.spring.desserteater.annotation.Fruity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/8/10
 */
@Component
@Cold
@Fruity
public class Popsicle implements Dessert {

    @Override
    public void eat() {
        System.out.println("Eating Popsicle");
    }
}
