package com.sigalhu.jse.springmvc.spittr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author huxujun
 * @date 2018/8/16
 */
@Configuration
@ComponentScan(
        basePackages = "com.sigalhu.jse.springmvc.spittr",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class))
public class RootConfig {
}
