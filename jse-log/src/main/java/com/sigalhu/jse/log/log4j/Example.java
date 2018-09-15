package com.sigalhu.jse.log.log4j;

import org.apache.log4j.Logger;

/**
 * @author huxujun
 * @date 2018/9/15
 */
public class Example {
    public static final Logger rootLogger = Logger.getRootLogger();
    public static final Logger infoLogger = Logger.getLogger("infoLogger");
    public static final Logger warnLogger = Logger.getLogger("warnLogger");
    public static final Logger errorLogger = Logger.getLogger("errorLogger");
}
