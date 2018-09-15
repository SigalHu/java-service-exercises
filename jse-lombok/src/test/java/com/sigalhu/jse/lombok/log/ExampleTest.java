package com.sigalhu.jse.lombok.log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/9/15
 */
public class ExampleTest {

    @Test
    public void getSlf4jLog() {
        Slf4jExample.getLog().info("slf4j info");
        Slf4jExample.getLog().warn("slf4j warn");
        Slf4jExample.getLog().error("slf4j error");
    }

    @Test
    public void getLog4jLog() {
        Log4jExample.getLog().info("log4j info");
        Log4jExample.getLog().warn("log4j warn");
        Log4jExample.getLog().error("log4j error");
    }

    @Test
    public void getLog4j2Log() {
        Log4j2Example.getLog().info("log4j2 info");
        Log4j2Example.getLog().warn("log4j2 warn");
        Log4j2Example.getLog().error("log4j2 error");
    }
}