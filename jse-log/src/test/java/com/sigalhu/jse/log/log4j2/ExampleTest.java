package com.sigalhu.jse.log.log4j2;

import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/9/10
 */
public class ExampleTest {

    @Test
    public void test() {
        Example.rootLogger.info("log4j2 root test!");
        Example.rootLogger.warn("log4j2 root test!");
        Example.rootLogger.error("log4j2 root test!");

        Example.infoLogger.info("log4j2 info test!");
        Example.infoLogger.warn("log4j2 info test!");
        Example.infoLogger.error("log4j2 info test!");

        Example.warnLogger.info("log4j2 warn test!");
        Example.warnLogger.warn("log4j2 warn test!");
        Example.warnLogger.error("log4j2 warn test!");

        Example.errorLogger.info("log4j2 error test!");
        Example.errorLogger.warn("log4j2 error test!");
        Example.errorLogger.error("log4j2 error test!");

        Example.packageLogger.info("log4j2 package test!");
        Example.packageLogger.warn("log4j2 package test!");
        Example.packageLogger.error("log4j2 package test!");
    }
}