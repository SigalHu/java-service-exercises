package com.sigalhu.jse.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huxujun
 * @date 2018/9/15
 */
public class Example {
    /**
     * 根据不同的桥接包，选择log4j.xml或log4j2.xml中的Logger
     */
    public static final Logger rootLogger = LoggerFactory.getLogger("rootLogger");
    public static final Logger infoLogger = LoggerFactory.getLogger("infoLogger");
    public static final Logger warnLogger = LoggerFactory.getLogger("warnLogger");
    public static final Logger errorLogger = LoggerFactory.getLogger("errorLogger");
}
