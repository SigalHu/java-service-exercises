package com.sigalhu.jse.cglib.beanutility;

import com.sigalhu.jse.cglib.targetobject.Dog;
import net.sf.cglib.beans.BeanGenerator;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class DogGenerator {

    public static Dog create() {
        BeanGenerator generator = new BeanGenerator();
        generator.setSuperclass(Dog.class);
        Dog dog = (Dog) generator.create();
        dog.setName("DogGenerator");
        return dog;
    }
}
