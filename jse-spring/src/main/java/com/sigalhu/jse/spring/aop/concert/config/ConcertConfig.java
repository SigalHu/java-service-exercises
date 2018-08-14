package com.sigalhu.jse.spring.aop.concert.config;

import com.sigalhu.jse.spring.aop.concert.Performance;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author huxujun
 * @date 2018/8/14
 */
@Configuration
//通过该注解启用自动代理功能
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = Performance.class)
public class ConcertConfig {
}
