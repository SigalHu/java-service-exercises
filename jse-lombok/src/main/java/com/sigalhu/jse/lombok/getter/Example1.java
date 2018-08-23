package com.sigalhu.jse.lombok.getter;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author huxujun
 * @date 2018/8/23
 */
//作用于非静态成员变量
@Getter
public class Example1 extends Person {

    @Getter
    private static int id;

    //注意：boolean类型的isOld，默认getter命名为isOld
    private boolean isOld;

    private boolean male;

    //第一次调用getter时会调用初始化函数获取并缓存结果
    //线程安全，且即使结果为空，也不会再调用初始化函数
    @Getter(lazy = true)
    private final String[] tags = createTags();

    public static void setId(int id) {
        Example1.id = id;
    }

    public void setOld(boolean old) {
        isOld = old;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    private String[] createTags() {
        System.out.println("calling createTags");
        return new String[]{"1", "2"};
    }
}

class Person {

    @Getter private String name;

    @Getter(AccessLevel.PRIVATE) private int age;

    public void setName(String name) {
        this.name = name;
    }
}
