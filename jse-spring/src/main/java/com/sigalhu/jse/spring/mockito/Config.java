package com.sigalhu.jse.spring.mockito;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author huxujun
 * @date 2018/10/14
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = Pet.class)
public class Config {
}
