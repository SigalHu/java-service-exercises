package com.sigalhu.jse.lombok.builder;

import lombok.Builder;
import lombok.Singular;

import java.util.Set;

/**
 * @author huxujun
 * @date 2018/8/26
 */
@Builder
public class Example1 {

    @Builder.Default
    private long created = System.currentTimeMillis();

    private String name;

    private int age;

    @Singular
    private Set<String> occupations;

    public long getCreated() {
        return created;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Set<String> getOccupations() {
        return occupations;
    }
}
