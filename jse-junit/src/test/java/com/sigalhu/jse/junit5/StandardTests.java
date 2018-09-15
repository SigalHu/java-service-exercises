package com.sigalhu.jse.junit5;

import org.junit.jupiter.api.*;

/**
 * @author huxujun
 * @date 2018/9/16
 */
@DisplayName("A special test case")
public class StandardTests {
    // @BeforeAll 只执行一次，执行时机是在所有测试和 @BeforeEach 注解方法之前
    @BeforeAll
    static void initAll() {
        System.out.println("init all");
    }

    // @BeforeEach 在每个测试执行之前执行
    @BeforeEach
    void init() {
        System.out.println("init");
    }

    // @Test注解在方法上标记方法为测试方法，以便构建工具和 IDE 能够识别并执行它们
    // JUnit 5不再需要手动将测试类与测试方法为public，包可见的访问级别就足够了
    @Test
    @DisplayName("╯°□°）╯")
    void succeedingTest() {
        System.out.println("succeeding test");
    }

    @Test
    @DisplayName("😱")
    void failingTest() {
        System.out.println("failing test");
        Assertions.fail("a failing test");
    }

    @Test
    // 通过 @Disabled 禁用单元测试
    @Disabled("for demonstration purposes")
    void skippedTest() {
        System.out.println("skipped test");
    }

    // @AfterEach 在每个测试执行之后执行
    @AfterEach
    void tearDown() {
        System.out.println("tear down");
    }

    // @AfterAll只执行一次，执行时机是在所有测试和 @AfterEach 注解方法之后
    @AfterAll
    static void tearDownAll() {
        System.out.println("tear down all");
    }
}
