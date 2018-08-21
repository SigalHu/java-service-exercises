package com.sigalhu.jse.lombok;

import lombok.Getter;

/**
 * @author huxujun
 * @date 2018/8/22
 */
@Getter
public class GetterExample {

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

    //    public String getName() {
//        return this.name;
//    }
//
//    public String getMob() {
//        return this.mob;
//    }
//
//    public String getSex() {
//        return this.sex;
//    }
}
