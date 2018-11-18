package com.sigalhu.jse.cglib.targetobject;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class CatImpl implements Cat {

    private String name;

    public CatImpl() {
        name = "Default";
    }

    public CatImpl(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String catBark() {
        return "Cat bark: meow meow meow! --- " + name;
    }
}
