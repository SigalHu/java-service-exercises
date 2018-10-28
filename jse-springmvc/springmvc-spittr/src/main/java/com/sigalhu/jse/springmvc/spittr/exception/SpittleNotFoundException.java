package com.sigalhu.jse.springmvc.spittr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 如果抛出该异常的话，响应将会具有404状态码，这是因为Spittle Not Found
 *
 * @author huxujun
 * @date 2018/10/28
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Spittle Not Found")
public class SpittleNotFoundException extends RuntimeException {
}
