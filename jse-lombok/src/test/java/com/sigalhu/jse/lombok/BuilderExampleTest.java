package com.sigalhu.jse.lombok;

import com.sigalhu.jse.lombok.BuilderExample;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/8/21
 */
public class BuilderExampleTest {

    @Test
    public void test() {
        BuilderExample builderExample = BuilderExample.builder()
                .name("sigal")
                .mob("1234567")
                .sex("male")
                .build();
        Assert.assertNotNull(builderExample);
        //builder注解会生成package访问权限的构造函数，因此会隐藏无参构造函数
        builderExample = new BuilderExample("sigal", "7654321", "male");
        Assert.assertNotNull(builderExample);
    }
}