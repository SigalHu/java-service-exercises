package com.sigalhu.jse.cglib.bean.immutable;

import com.sigalhu.jse.cglib.targetobject.Dog;
import net.sf.cglib.beans.ImmutableBean;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class ImmutableDog {

    public static Dog create() {
        Dog dog = new Dog();
        dog.setName("ImmutableDog");
        return (Dog) ImmutableBean.create(dog);
    }
}
