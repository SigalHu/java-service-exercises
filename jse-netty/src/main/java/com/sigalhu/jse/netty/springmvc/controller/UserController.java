package com.sigalhu.jse.netty.springmvc.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huxujun
 * @date 2018/12/16
 */
@Controller
@RequestMapping(value="/user",produces = "text/json;charset=utf-8")
public class UserController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    @ResponseBody
    public String login(String username,String pwd){
        JSONObject resultJson = new JSONObject();
        Map<String, Object> loginResult = new HashMap<>();
        loginResult.put("username", username);
        loginResult.put("age", 25);
        loginResult.put("sex", "男");

        resultJson.put("code", 200);
        resultJson.put("msg", "登录成功");
        resultJson.put("result", loginResult);

        return JSONObject.toJSONString(resultJson);
    }
}