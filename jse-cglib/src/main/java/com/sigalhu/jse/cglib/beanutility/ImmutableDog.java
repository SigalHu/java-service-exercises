package com.sigalhu.jse.cglib.beanutility;

import com.sigalhu.jse.cglib.targetobject.DogImpl;
import net.sf.cglib.beans.ImmutableBean;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class ImmutableDog {

    public static DogImpl create() {
        DogImpl dog = new DogImpl();
        dog.setName("ImmutableDog");
        return (DogImpl) ImmutableBean.create(dog);
    }
}
