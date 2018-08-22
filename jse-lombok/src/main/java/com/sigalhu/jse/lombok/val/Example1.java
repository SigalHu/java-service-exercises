package com.sigalhu.jse.lombok.val;

import lombok.val;

import java.util.ArrayList;

/**
 * @author huxujun
 * @date 2018/8/22
 */
public class Example1 {

    public String example() {
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        example.add("Hello, Java!");
        val foo = example.get(0);
        //val推断的类型被final修饰
//        foo = example.get(1);
        return foo.toLowerCase();
    }
}
