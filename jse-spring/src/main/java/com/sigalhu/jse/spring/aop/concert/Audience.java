package com.sigalhu.jse.spring.aop.concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/8/14
 */
//该注解表明Audience是一个切面，Audience类中的方法都使用注解来定义切面的具体行为
@Aspect
@Component
public class Audience {

    //该注解能够在切面内定义可重用的切点
    @Pointcut("execution(* com.sigalhu.jse.spring.aop.concert.Performance.perform(..))")
    //该方法的实际内容并不重要，其本身只是一个标识，供@Pointcut注解依附
    public void performance() {}

    //该注解声明的通知方法会在目标方法调用之前执行
    @Before("performance()")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }

    //within()指示器限制连接点匹配指定的类型
    @Before("performance() && within(com.sigalhu.jse.spring.aop.concert.*)")
    public void takeSeats() {
        System.out.println("Taking seats");
    }

    //该注解声明的通知方法会将目标方法封装起来
    //bean()指示器使用bean id或bean名称作为参数来限制切点只匹配特定的bean
    @Around("performance() && !bean(badPerformance)")
    public void watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("Starting the performance");
            joinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("Occurring an exception");
            throw e;
        }
        System.out.println("Ending the performance");
    }

    //该注解声明的通知方法会在目标方法返回后调用
    @AfterReturning("performance()")
    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }

    //该注解声明的通知方法会在目标方法抛出异常后调用
    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("Demanding a refund");
    }

    //该注解声明的通知方法会在目标方法返回或抛出异常后调用
    @After("performance()")
    public void leave() {
        System.out.println("Leaving");
    }
}
