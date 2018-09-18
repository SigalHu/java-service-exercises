package com.sigalhu.jse.junit5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * 1. @Disabled注解在测试类上，整个类中的测试方法，都会被跳过
 * 2. @Disabled注解在测试方法上，被注解的方法不会执行（将被跳过），但会报告为已执行
 *
 * @author huxujun
 * @date 2018/9/19
 */
@Disabled
public class DisabledDemo {

    @Disabled
    @Test
    void testWillBeSkipped() {
    }

    @Test
    void testWillBeExecuted() {
    }
}
