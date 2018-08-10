package com.sigalhu.jse.spring.person;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/8/10
 */
@Component
public class ScanPerson {

    //使用该注解将定义在外部属性文件中的属性，通过占位符值将其插入到bean中
    @Value("${scan.person.name}")
    private String name;

    @Value("${scan.person.age}")
    private Integer age;

    @Value("${scan.person.mob}")
    private String mob;

    //属性值为中文时乱码
    @Value("${scan.person.sex}")
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
        return "ScanPerson(name=" + this.getName() + ", age=" + this.getAge() + ", mob=" + this.getMob() + ", sex=" + this.getSex() + ")";
    }
}
