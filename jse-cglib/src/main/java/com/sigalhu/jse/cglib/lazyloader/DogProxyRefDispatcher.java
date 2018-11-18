package com.sigalhu.jse.cglib.lazyloader;

import com.sigalhu.jse.cglib.targetobject.Dog;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.ProxyRefDispatcher;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class DogProxyRefDispatcher {

    private Dog dog;

    public DogProxyRefDispatcher() {
        System.out.println("DogProxyRefDispatcher DogProxyRefDispatcher()");
        dog = (Dog) Enhancer.create(Dog.class,
                //ProxyRefDispatcher回调和Dispatcher一样，不同的是，它可以把代理对象作为装载对象方法的一个参数传递
                new ProxyRefDispatcher() {
                    @Override
                    public Object loadObject(Object proxy) throws Exception {
                        System.out.println("DogProxyRefDispatcher loadObject()");
                        return new Dog("DogProxyRefDispatcher");
                    }
                });
    }

    public String dogBark() {
        System.out.println("DogProxyRefDispatcher dogBark()");
        return dog.bark();
    }
}
