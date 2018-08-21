package com.sigalhu.jse.lombok;

import lombok.ToString;

/**
 * @author huxujun
 * @date 2018/8/22
 */
@ToString
public class ToStringExample {

    private String name;

    private String mob;

    private String sex;

    public void setName(String name) {
        this.name = name;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

//    public String toString() {
//        return "ToStringExample(name=" + this.getName() + ", mob=" + this.getMob() + ", sex=" + this.getSex() + ")";
//    }
}
