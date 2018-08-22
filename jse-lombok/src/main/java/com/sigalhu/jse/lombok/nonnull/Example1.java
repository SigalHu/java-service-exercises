package com.sigalhu.jse.lombok.nonnull;

import lombok.NonNull;

/**
 * @author huxujun
 * @date 2018/8/22
 */
public class Example1 extends Person {

    private Integer age;

    public Example1(String name, @NonNull Integer age) {
        super(name);
//        if (age == null) {
//            throw new NullPointerException("age is marked @NonNull but is null");
//        }
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }
}

class Person {

    private String name;

    public Person(@NonNull String name) {
//        if (name == null) {
//            throw new NullPointerException("name is marked @NonNull but is null");
//        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
