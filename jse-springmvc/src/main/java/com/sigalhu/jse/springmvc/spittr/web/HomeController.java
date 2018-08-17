package com.sigalhu.jse.springmvc.spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author huxujun
 * @date 2018/8/17
 */
//声明为一个控制器
@Controller
//该注解声明来所要处理的请求
//value属性指定了该方法所要处理的请求路径
@RequestMapping({"/", "/home"})
public class HomeController {

    //method属性细化了它所处理的Http方法
    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
