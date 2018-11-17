package com.sigalhu.jse.cglib.targetobject;

/**
 * @author huxujun
 * @date 2018/11/16
 */
public class Dog {

    private String name;

    public Dog() {
        name = "Default";
    }

    public Dog(String name) {
        this.name = name;
    }

    public String bark() {
        return "Dog bark: wow wow wow! --- " + name;
    }
}
