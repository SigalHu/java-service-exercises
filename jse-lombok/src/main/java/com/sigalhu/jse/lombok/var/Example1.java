package com.sigalhu.jse.lombok.var;

import lombok.var;

import java.util.ArrayList;

/**
 * @author huxujun
 * @date 2018/8/22
 */
public class Example1 {

    public String example() {
        var example = new ArrayList<String>();
        example.add("Hello, World!");
        example.add("Hello, Java!");
        var foo = example.get(0);
        //var推断的类型不被final修饰
        foo = example.get(1);
        //foo已被推断为String类型
//        foo = 1;
        return foo.toLowerCase();
    }
}
