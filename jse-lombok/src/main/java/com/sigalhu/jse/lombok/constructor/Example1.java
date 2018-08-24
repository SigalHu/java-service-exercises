package com.sigalhu.jse.lombok.constructor;

import lombok.*;

/**
 * @author huxujun
 * @date 2018/8/24
 */
//该注解用来生成无参构造函数
//force属性为true时，final修饰的未初始化的成员变量被初始化为0/false/null
//通过access属性可以设置构造函数的访问权限，默认为public
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
//该注解用来生成特定的有参构造函数，初始化被final修饰或@NonNull注解的未初始化的非静态成员变量
//@NonNull注解的成员变量在被初始化前会有null check
//使用staticName属性生成
@RequiredArgsConstructor(staticName = "of")
//该注解用来生成有参构造函数，初始化被final修饰的未初始化的非静态成员变量与普通非静态成员变量
@AllArgsConstructor
public class Example1 {

    private final int id;

    @NonNull
    private String name;

    private final int weight = 125;

    @NonNull
    private Integer height = 175;

    private String sex;

    private static int age;

    @NonNull
    private static String tag;

//    private Example1() {
//        this.id = 0;
//        this.name = null;
//    }
//
//    @java.beans.ConstructorProperties({"id", "name"})
//    private Example1(int id, String name) {
//        if (name == null) {
//            throw new NullPointerException("name is marked @NonNull but is null");
//        }
//        this.id = id;
//        this.name = name;
//    }
//
//    public static Example1 of(int id, String name) {
//        return new Example1(id, name);
//    }
//
//    @java.beans.ConstructorProperties({"id", "name", "height", "sex"})
//    public Example1(int id, String name, Integer height, String sex) {
//        if (name == null) {
//            throw new NullPointerException("name is marked @NonNull but is null");
//        }
//        if (height == null) {
//            throw new NullPointerException("height is marked @NonNull but is null");
//        }
//        this.id = id;
//        this.name = name;
//        this.height = height;
//        this.sex = sex;
//    }
}
