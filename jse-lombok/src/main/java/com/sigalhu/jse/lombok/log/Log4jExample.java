package com.sigalhu.jse.lombok.log;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;

/**
 * @author huxujun
 * @date 2018/9/15
 */
@Log4j(topic = "errorLogger")
public class Log4jExample {
    public static Logger getLog() {
        return log;
    }
}
