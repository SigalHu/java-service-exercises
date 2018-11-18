package com.sigalhu.jse.cglib.enhancer;

import com.sigalhu.jse.cglib.targetobject.Dog;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * 在cglib回调时可以设置对不同方法执行不同的回调逻辑，或者根本不执行回调。
 *
 * @author huxujun
 * @date 2018/11/16
 */
public class DogEnhancer implements CallbackFilter {

    public static Dog create() {
        Enhancer enhancer = new Enhancer();
        //设置产生的代理对象的父类
        enhancer.setSuperclass(Dog.class);
        //设置方法回调过滤器
        enhancer.setCallbackFilter(new DogEnhancer());
        //设置多个CallBack接口的实例
        enhancer.setCallbacks(new Callback[]{
                //锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
                new FixedValue() {
                    @Override
                    public Object loadObject() throws Exception {
                        return "Hello cglib!";
                    }
                },
                //在调用目标方法时，cglib会回调MethodInterceptor接口方法拦截，来实现你自己的代理逻辑
                //注意：
                //1、若原方法的参数存在基本类型，则对于第三个参数Object[] args会被转化成类的类型
                //2、若原方法为final方法，则MethodInterceptor接口无法拦截该方法
                new MethodInterceptor() {
                    /**
                     * @param obj    由cglib动态生成的代理类实例
                     * @param method 实体类所调用的被代理的方法引用
                     * @param args   参数值列表
                     * @param proxy  生成的代理类对方法的代理引用
                     * @return 从代理实例的方法调用返回的值
                     * @throws Throwable
                     */
                    @Override
                    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        //调用代理类实例上的proxy方法的父类方法，即实体类Dog中对应的方法
                        return "[cglib] " + proxy.invokeSuper(obj, args);
                    }
                },
                //NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
                NoOp.INSTANCE
        });
        //使用有参数的构造函数创建目标对象。参数Class[]定义了参数的类型，第二个Object[]是参数的值
        return (Dog) enhancer.create(
                new Class[]{String.class},
                new Object[]{"DogEnhancer"});
    }

    /**
     * 定义实现过滤器CallbackFilter接口的类，其中返回值为被代理类的各个方法在回调数组Callback[]中的位置索引
     *
     * @param method
     * @return
     */
    @Override
    public int accept(Method method) {
        if (method.getReturnType().equals(String.class)) {
            if (!Dog.class.equals(method.getDeclaringClass())) {
                return 0;
            } else {
                return 1;
            }
        }
        return 2;
    }
}
