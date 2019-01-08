package com.sigalhu.jse.netty.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huxujun
 * @date 2018/12/14
 */
@Controller
@RequestMapping(value = {"/", "/home"}, produces = "text/html;charset=utf-8")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    String home(HttpServletRequest request) {
        return "hello! 你好！";
    }
}
