package com.sigalhu.jse.cglib.mixin;

import com.sigalhu.jse.cglib.targetobject.Cat;
import com.sigalhu.jse.cglib.targetobject.CatImpl;
import com.sigalhu.jse.cglib.targetobject.Dog;
import com.sigalhu.jse.cglib.targetobject.DogImpl;
import net.sf.cglib.proxy.Mixin;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class PetMixin {

    public static Object create() {
        return Mixin.create(
                new Class[]{Dog.class, Cat.class},
                new Object[]{new DogImpl("DogMixin"), new CatImpl("CatMixin")});
    }
}
