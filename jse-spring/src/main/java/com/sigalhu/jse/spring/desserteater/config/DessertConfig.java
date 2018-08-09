package com.sigalhu.jse.spring.desserteater.config;

import com.sigalhu.jse.spring.desserteater.Dessert;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author huxujun
 * @date 2018/8/9
 */
@Configuration
@ComponentScan(basePackageClasses = Dessert.class)
public class DessertConfig {
}
