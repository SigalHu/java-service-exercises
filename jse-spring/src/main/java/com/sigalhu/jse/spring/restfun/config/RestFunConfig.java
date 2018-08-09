package com.sigalhu.jse.spring.restfun.config;

import com.sigalhu.jse.spring.restfun.MagicBean;
import com.sigalhu.jse.spring.restfun.MagicExistCondition;
import org.springframework.context.annotation.*;

/**
 * @author huxujun
 * @date 2018/8/9
 */
@Configuration
//使用该注解导入配置类
@Import(MagicConfig.class)
//使用该注解导入xml配置
@ImportResource("classpath*:restfun.xml")
public class RestFunConfig {
}
