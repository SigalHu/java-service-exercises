package com.sigalhu.jse.cglib.mixin;

import com.sigalhu.jse.cglib.targetobject.Cat;
import com.sigalhu.jse.cglib.targetobject.Dog;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class PetMixinTest {

    @Test
    public void create() {
        Object object = PetMixin.create();
        Assert.assertNotNull(object);

        Assert.assertEquals("Dog bark: wow wow wow! --- DogMixin", ((Dog) object).dogBark());
        Assert.assertEquals("Cat bark: meow meow meow! --- CatMixin", ((Cat) object).catBark());
    }
}