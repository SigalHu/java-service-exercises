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

    public void setDogName(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String bark() {
        return "Dog bark: wow wow wow! --- " + name;
    }
}
