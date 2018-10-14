package com.sigalhu.jse.spring.mockito;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/10/14
 */
@Aspect
@Component
public class Master {

    @Pointcut("execution(* com.sigalhu.jse.spring.mockito.AnotherHouse.petBark())")
    public void listen() {
    }

    @Before("listen()")
    public void beforePetBark() {
        System.out.println("Before pet bark...");
    }

    @After("listen()")
    public void afterPetBark() {
        System.out.println("After pet bark!");
    }
}
