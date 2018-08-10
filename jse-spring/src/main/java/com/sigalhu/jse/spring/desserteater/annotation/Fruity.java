package com.sigalhu.jse.spring.desserteater.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建自定义的限定符注解，借助这样的注解来表达bean所希望限定的特性
 *
 * @author huxujun
 * @date 2018/8/10
 */
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD,
        ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//需要使用该注解来标注
@Qualifier
public @interface Fruity {
}
