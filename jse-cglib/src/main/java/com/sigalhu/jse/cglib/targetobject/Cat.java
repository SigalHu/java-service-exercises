package com.sigalhu.jse.cglib.targetobject;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class Cat {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String bark() {
        return "Cat bark: meow meow meow! --- " + name;
    }
}
