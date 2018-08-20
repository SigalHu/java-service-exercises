package com.sigalhu.jse.springmvc.spittr.web;

import com.sigalhu.jse.springmvc.spittr.Spitter;
import com.sigalhu.jse.springmvc.spittr.data.SpitterRepository;
import com.sigalhu.jse.springmvc.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author huxujun
 * @date 2018/8/17
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    //处理对/spitter/register的GET请求
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //Spitter对象的属性将会使用请求中同名的参数进行填充
    public String processRegistration(
            //使用该注解标注要校验的参数
            @Valid Spitter spitter,
            //Errors参数要紧跟在带有@Valid注解的参数后面
            Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }

        spitterRepository.save(spitter);
        //重定向到基本信息页，当InternalResourceViewResolver看到视图格式中的redirect:前缀时，
        //它就知道要将其解析为重定向的规则，而不是视图的名称
        //此外，当视图格式中以forward:作为前缀时，请求将会前往指定的url路径
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(Model model,
                                     @PathVariable String username) {
        Spitter spitter = spitterRepository.findByUsername(username);
        model.addAttribute(spitter);
        return "profile";
    }
}
