package com.sigalhu.jse.spring.person.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author huxujun
 * @date 2018/8/10
 */
@Configuration
@Import(CodePersonConfig.class)
//@ImportResource("classpath*:code-person.xml")
public class PersonConfig {
}
