package com.sigalhu.jse.serialize.beans;

import io.protostuff.Exclude;
import io.protostuff.Tag;
import lombok.Data;

import java.util.Date;

/**
 * @author huxujun
 * @date 2019/1/17
 */
@Data
public class Person {

    @Tag(1)
    private Long id;

    @Tag(2)
    private String name;

    @Tag(3)
    private Integer age;

    @Tag(4)
    private Double height;

    @Exclude
    private Date updateTime;

    @Tag(5)
    private Date createTime;
}
