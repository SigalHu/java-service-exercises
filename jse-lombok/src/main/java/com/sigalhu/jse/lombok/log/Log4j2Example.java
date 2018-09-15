package com.sigalhu.jse.lombok.log;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;

/**
 * @author huxujun
 * @date 2018/9/15
 */
@Log4j2(topic = "errorLogger")
public class Log4j2Example {
    public static Logger getLog() {
        return log;
    }
}
