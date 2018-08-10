package com.sigalhu.jse.spring.person;

import java.util.List;

/**
 * @author huxujun
 * @date 2018/8/10
 */
public class Family {

    private List<CodePerson> people;

    private List<String> names;

    public List<CodePerson> getPeople() {
        return this.people;
    }

    public List<String> getNames() {
        return this.names;
    }

    public void setPeople(List<CodePerson> people) {
        this.people = people;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "Family(people=" + this.getPeople() + ", names=" + this.getNames() + ")";
    }
}
