package com.sigalhu.jse.log.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author huxujun
 * @date 2018/9/10
 */
public class Example {
    /**
     * 获取log4j2.xml文件中定义的名为“MainLogger”的Logger
     * 如果没有对应名称的Logger，则无法使用Logger记录和输出日志信息
     */
    private static final Logger log = LogManager.getLogger("MainLogger");

    public void info(String msg) {
        log.info(msg);
    }

    public void error(String msg) {
        log.error(msg);
    }
}

