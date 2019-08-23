package com.sigalhu.jse.log.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author huxujun
 * @date 2018/9/10
 */
public class Example {
    public static final Logger rootLogger = LogManager.getRootLogger();
    public static final Logger infoLogger = LogManager.getLogger("infoLogger");
    public static final Logger warnLogger = LogManager.getLogger("warnLogger");
    public static final Logger errorLogger = LogManager.getLogger("errorLogger");
    public static final Logger packageLogger = LogManager.getLogger(Example.class);
}

