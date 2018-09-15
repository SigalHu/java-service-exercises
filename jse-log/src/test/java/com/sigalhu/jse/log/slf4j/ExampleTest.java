package com.sigalhu.jse.log.slf4j;

import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/9/15
 */
public class ExampleTest {

    @Test
    public void test() {
        Example.rootLogger.info("slf4j root test!");
        Example.rootLogger.warn("slf4j root test!");
        Example.rootLogger.error("slf4j root test!");

        Example.infoLogger.info("slf4j info test!");
        Example.infoLogger.warn("slf4j info test!");
        Example.infoLogger.error("slf4j info test!");

        Example.warnLogger.info("slf4j warn test!");
        Example.warnLogger.warn("slf4j warn test!");
        Example.warnLogger.error("slf4j warn test!");

        Example.errorLogger.info("slf4j error test!");
        Example.errorLogger.warn("slf4j error test!");
        Example.errorLogger.error("slf4j error test!");
    }
}