package com.sigalhu.jse.cglib.targetobject;

/**
 * @author huxujun
 * @date 2018/11/16
 */
public class DogImpl implements Dog {

    private String name;

    public DogImpl() {
        name = "Default";
    }

    public DogImpl(String name) {
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

    @Override
    public String dogBark() {
        return "Dog bark: wow wow wow! --- " + name;
    }
}
