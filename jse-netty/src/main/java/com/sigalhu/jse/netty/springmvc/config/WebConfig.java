package com.sigalhu.jse.netty.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author huxujun
 * @date 2018/12/16
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.sigalhu.jse.netty.springmvc",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class WebConfig extends WebMvcConfigurerAdapter {
}
