package com.sigalhu.jse.lombok.tostring;

import lombok.ToString;

/**
 * @author huxujun
 * @date 2018/8/22
 */
//callSuper属性为true时调用父类toString函数
@ToString(callSuper = true)
public class Example1 extends Square {
    //ToString注解默认只打印非静态成员变量，可通过Include注解打印
    //rank属性的值越高，优先级越高，默认为0
    @ToString.Include(rank = 1) private static final int STATIC_VAR = 10;
    //通过Exclude注解排除不想打印的成员变量
    @ToString.Exclude private int id;
    @ToString.Include(rank = 2) private String name;
    private String[] tags;

    public Example1(int id, String name, int width, int height, String[] tags) {
        super(width, height);
        this.id = id;
        this.name = name;
        this.tags = tags;
    }
}

//onlyExplicitlyIncluded属性为true时，只打印注解Include的成员变量
//includeFieldNames属性为false时，不打印字段名
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
class Square {
    //通过name属性可以改变打印的字段名
    @ToString.Include(name = "other name") private final int width;
    private final int height;

    public Square(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
