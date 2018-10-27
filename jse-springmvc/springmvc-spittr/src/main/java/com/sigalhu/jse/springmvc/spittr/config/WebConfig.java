package com.sigalhu.jse.springmvc.spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author huxujun
 * @date 2018/8/16
 */
@Configuration
//启用Spring MVC
@EnableWebMvc
//启用组件扫描
@ComponentScan("com.sigalhu.jse.springmvc.spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    //配置JSP视图解析器
    @Bean
    public ViewResolver viewResolver() {
        //InternalResourceViewResolver会查找JSP文件，在查找的时候，会在视图名称上加一个特定的前缀和后缀
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    //配置multipart解析器
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    //配置静态资源的处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //通过该方法要求DispatcherServlet将对静态资源的请求转发到Servlet中默认的Servlet上，
        //而不是使用DispatcherServlet本身来处理此类请求
        configurer.enable();
    }
}
