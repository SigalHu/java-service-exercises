package com.sigalhu.jse.spring.person;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author huxujun
 * @date 2018/8/10
 */
public class CodePerson {

    private String name;

    private Integer age;

    private String mob;

    private String sex;

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getMob() {
        return this.mob;
    }

    public String getSex() {
        return this.sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "CodePerson(name=" + this.getName() + ", age=" + this.getAge() + ", mob=" + this.getMob() + ", sex=" + this.getSex() + ")";
    }
}
