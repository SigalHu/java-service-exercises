package com.sigalhu.jse.log.log4j2;

import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/9/10
 */
public class ExampleTest {

    @Test
    public void test() {
        Example.rootLogger.info("root test!");
        Example.rootLogger.warn("root test!");
        Example.rootLogger.error("root test!");

        Example.infoLogger.info("info test!");
        Example.infoLogger.warn("info test!");
        Example.infoLogger.error("info test!");

        Example.warnLogger.info("warn test!");
        Example.warnLogger.warn("warn test!");
        Example.warnLogger.error("warn test!");

        Example.errorLogger.info("error test!");
        Example.errorLogger.warn("error test!");
        Example.errorLogger.error("error test!");
    }
}