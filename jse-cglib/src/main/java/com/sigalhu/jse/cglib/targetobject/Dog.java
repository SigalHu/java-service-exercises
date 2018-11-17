package com.sigalhu.jse.cglib.targetobject;

import java.util.Objects;

/**
 * @author huxujun
 * @date 2018/11/16
 */
public class Dog {

    public String bark(String name) {
        if (Objects.isNull(name)) {
            name = "default";
        }
        return "Dog bark: wow wow wow! --- " + name;
    }
}
