package com.sigalhu.jse.lombok.equalsandhashcode;

import lombok.EqualsAndHashCode;

/**
 * @author huxujun
 * @date 2018/8/24
 */
//callSuper属性为true时调用父类toString函数
@EqualsAndHashCode(callSuper = true)
public class Example1 extends Square {

    //EqualsAndHashCode注解默认只作用于non-static/non-transient成员变量
    //但可通过Include注解使其作用于该成员变量
    @EqualsAndHashCode.Include
    private transient int id;

    @EqualsAndHashCode.Include
    private static String name;

    private transient int age;

    //通过Exclude注解排除成员变量
    @EqualsAndHashCode.Exclude
    private String sex;

    public void setId(int id) {
        this.id = id;
    }

    public static void setName(String name) {
        Example1.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

//onlyExplicitlyIncluded属性为true时，只打印注解Include的成员变量
//includeFieldNames属性为false时，不打印字段名
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class Square {

    @EqualsAndHashCode.Include
    private int width;

    private int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

