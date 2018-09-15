package com.sigalhu.jse.lombok.setter;

import lombok.AccessLevel;
import lombok.Setter;

/**
 * @author huxujun
 * @date 2018/8/23
 */
//作用于非静态成员变量
@Setter
public class Example1 extends Person {

    @Setter
    private static int id;

    //注意：boolean类型的isOld，默认setter命名为setOld
    private boolean isOld;

    private boolean male;

    public static int getId() {
        return id;
    }

    public boolean isOld() {
        return isOld;
    }

    public boolean isMale() {
        return male;
    }
}

class Person {

    @Setter
    private String name;

    @Setter(AccessLevel.PRIVATE)
    private int age;

    public String getName() {
        return name;
    }
}