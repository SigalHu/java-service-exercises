package com.sigalhu.jse.log.log4j;

import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/9/15
 */
public class ExampleTest {

    @Test
    public void test() {
        Example.rootLogger.info("log4j root test!");
        Example.rootLogger.warn("log4j root test!");
        Example.rootLogger.error("log4j root test!");

        Example.infoLogger.info("log4j info test!");
        Example.infoLogger.warn("log4j info test!");
        Example.infoLogger.error("log4j info test!");

        Example.warnLogger.info("log4j warn test!");
        Example.warnLogger.warn("log4j warn test!");
        Example.warnLogger.error("log4j warn test!");

        Example.errorLogger.info("log4j error test!");
        Example.errorLogger.warn("log4j error test!");
        Example.errorLogger.error("log4j error test!");

        Example.packageLogger.info("log4j package test!");
        Example.packageLogger.warn("log4j package test!");
        Example.packageLogger.error("log4j package test!");
    }
}