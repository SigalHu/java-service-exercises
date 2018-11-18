package com.sigalhu.jse.cglib.lazyloader;

import com.sigalhu.jse.cglib.targetobject.DogImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.LazyLoader;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class DogLazyLoader {

    private DogImpl dog;

    public DogLazyLoader() {
        System.out.println("DogLazyLoader DogLazyLoader()");
        dog = (DogImpl) Enhancer.create(DogImpl.class,
                //对需要延迟加载的对象添加代理，在获取该对象属性时先通过代理类回调方法进行对象初始化
                //在不需要加载该对象时，只要不去获取该对象内属性，该对象就不会被初始化了
                new LazyLoader() {
                    @Override
                    public Object loadObject() throws Exception {
                        System.out.println("LazyLoader loadObject()");
                        return new DogImpl("DogLazyLoader");
                    }
                });
    }

    public String dogBark() {
        System.out.println("DogLazyLoader dogBark()");
        return dog.dogBark();
    }
}
