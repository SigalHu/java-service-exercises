package com.sigalhu.jse.serialize.beans;

import io.protostuff.Exclude;
import io.protostuff.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huxujun
 * @date 2019/1/17
 */
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = -1550213275663745027L;

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
