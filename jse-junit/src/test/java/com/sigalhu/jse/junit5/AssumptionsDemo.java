package com.sigalhu.jse.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

/**
 * 前置条件 (Assumption) 与断言类似，但前置条件必须为 true，否则测试将中止。
 * 与此相反，当断言失败时，则将测试视为已失败。
 *
 * @author huxujun
 * @date 2018/9/18
 */
public class AssumptionsDemo {

    @Test
    void testOnlyOnCiServer() {
        // 如果该条件不成立，则跳过该测试。在此情况下，不会导致构建失败
        // 会跳过 assumeTrue() 后的测试方法中的所有代码。
        Assumptions.assumeTrue("CI".equals(System.getenv("ENV")));
        // remainder of test
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        Assumptions.assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation");
        // remainder of test
    }

    @Test
    void testInAllEnvironments() {
        // 如果在前置条件成立时仅应执行测试方法的一部分，可以使用 assumingThat() 方法
        // 无论 assumingThat() 中的前置条件成立与否，都会执行 lambda 表达式后的所有代码
        Assumptions.assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    // perform these assertions only on the CI server
                    Assertions.assertEquals(2, 2);
                });

        // perform these assertions in all environments
        Assertions.assertEquals("a string", "a string");
    }
}
