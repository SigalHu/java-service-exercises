package com.sigalhu.jse.springmvc.spittr.web;

import com.sigalhu.jse.springmvc.spittr.exception.DuplicateSpittleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 在带有@ControllerAdvice注解的类中，@ExceptionHandler、@InitBinder、@ModelAttribute注解
 * 标注的方法会运用到整个应用程序所有控制器中带有@RequestMapping注解的方法上
 *
 * @author huxujun
 * @date 2018/10/27
 */
@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleNotFound() {
        return "error/duplicate";
    }
}