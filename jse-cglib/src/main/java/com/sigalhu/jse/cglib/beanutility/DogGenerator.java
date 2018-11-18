package com.sigalhu.jse.cglib.beanutility;

import com.sigalhu.jse.cglib.targetobject.DogImpl;
import net.sf.cglib.beans.BeanGenerator;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class DogGenerator {

    public static DogImpl create() {
        BeanGenerator generator = new BeanGenerator();
        generator.setSuperclass(DogImpl.class);
        DogImpl dog = (DogImpl) generator.create();
        dog.setName("DogGenerator");
        return dog;
    }
}
