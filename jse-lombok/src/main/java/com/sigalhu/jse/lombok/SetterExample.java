package com.sigalhu.jse.lombok;

import lombok.Setter;

/**
 * @author huxujun
 * @date 2018/8/21
 */
@Setter
public class SetterExample {

    private String name;

    private String mob;

    private String sex;

    public String getName() {
        return name;
    }

    public String getMob() {
        return mob;
    }

    public String getSex() {
        return sex;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setMob(String mob) {
//        this.mob = mob;
//    }
//
//    public void setSex(String sex) {
//        this.sex = sex;
//    }
}
