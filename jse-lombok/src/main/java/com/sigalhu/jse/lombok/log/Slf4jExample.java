package com.sigalhu.jse.lombok.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * @author huxujun
 * @date 2018/9/15
 */
@Slf4j(topic = "warnLogger")
public class Slf4jExample {
    public static Logger getLog() {
        return log;
    }
}
