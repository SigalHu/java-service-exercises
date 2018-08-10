package com.sigalhu.jse.spring.person.config;

import com.sigalhu.jse.spring.person.CodePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import java.nio.charset.StandardCharsets;

/**
 * @author huxujun
 * @date 2018/8/10
 */
@Configuration
@ComponentScan(basePackageClasses = CodePerson.class)
//声明属性源
@PropertySource("classpath:person.properties")
public class CodePersonConfig {

    //属性文件会加载到Spring的Environment中
    @Autowired
    Environment environment;

    @Bean
    public CodePerson codePerson() {
        CodePerson person = new CodePerson();
        person.setName(environment.getProperty("code.person.name"));
        //将String类型的值转化为Integer类型
        person.setAge(environment.getProperty("code.person.age", Integer.class));
        //在指定属性不存在时，返回默认值
        person.setMob(environment.getProperty("code.person.mob", String.class, "1111111"));
        //使用该方法，若属性未定义，将抛出IllegalStateException异常
        byte[] bytes = environment.getRequiredProperty("code.person.sex").getBytes(StandardCharsets.ISO_8859_1);
        person.setSex(new String(bytes, StandardCharsets.UTF_8));
        return person;
    }

    //为了使用占位符必须配置一个PropertySourcesPlaceholderConfigurer bean
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
