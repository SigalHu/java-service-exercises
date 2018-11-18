package com.sigalhu.jse.cglib.lazyloader;

import com.sigalhu.jse.cglib.targetobject.DogImpl;
import net.sf.cglib.proxy.Dispatcher;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class DogDispatcher {

    private DogImpl dog;

    public DogDispatcher() {
        System.out.println("DogDispatcher DogDispatcher()");
        dog = (DogImpl) Enhancer.create(DogImpl.class,
                //Dispatcher在每次访问延迟加载属性时都会触发代理类回调方法
                new Dispatcher() {
                    @Override
                    public Object loadObject() throws Exception {
                        System.out.println("Dispatcher loadObject()");
                        return new DogImpl("DogDispatcher");
                    }
                });
    }

    public String dogBark() {
        System.out.println("DogDispatcher dogBark()");
        return dog.dogBark();
    }
}
