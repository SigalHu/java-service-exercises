package com.sigalhu.jse.lombok.value;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.Wither;

/**
 * @author huxujun
 * @date 2018/8/25
 */
//该注解是@Data的变体，通过该注解，所有成员变量都默认被private/final修饰，并且不会生成setter
//同时其注解的类同样默认被final修饰
@Value
public class Example1 {

    String name;

    @Wither(AccessLevel.PACKAGE)
    @NonFinal
    int age;

    double score;

    protected String[] tags;

    @Value(staticConstructor = "of")
    public static class Exercise<T> {
        String name;
        T value;
    }
}
