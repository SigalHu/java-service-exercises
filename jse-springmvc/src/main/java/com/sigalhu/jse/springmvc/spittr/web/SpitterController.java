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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            //RedirectAttributes提供了Model的所有功能，除此之外，还有几个方法用来设置flash属性
            RedirectAttributes model,
            @RequestPart("profilePicture") MultipartFile profilePicture,
            //使用该注解标注要校验的参数
            @Valid Spitter spitter,
            //Errors参数要紧跟在带有@Valid注解的参数后面
            Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }

        spitterRepository.save(spitter);
        //重定向到基本信息页，当InternalResourceViewResolver看到视图格式中的redirect:前缀时，
        //它就知道要将其解析为重定向的规则，而不是视图的名称，此外，当视图格式中以forward:作为前缀时，请求将会前往指定的url路径
        //将username作为占位符填充到url模板中，而不是直接连接到重定向String中，可以使username中所有的不安全字符进行转义
        model.addAttribute("username", spitter.getUsername());

        //一般来说，当一个处理器方法完成之后，该方法所指定的模型数据将会复制到请求中，并作为请求中的属性，请求会转发到视图上进行渲染，
        //因为控制器方法和视图所处理的是同一个请求，所以在转发的过程中，请求属性能够得以保存，但是，当控制器的结果是重定向的话，
        //原始的请求就结束了，并且会发起一个新的GET请求，原始请求中所带有的模型数据也就随着请求一起消亡了，在新的请求属性中，没有任何的模型数据

        //因为模型中的spitterId属性没有匹配重定向url中的任何占位符，所以它会自动以查询参数的形式附加到重定向url上
        model.addAttribute("spitterId", spitter.getId());
        //falsh属性会一直携带这些数据直到下一次请求，然后才会消失，在重定向执行之前，所有的flash属性都会复制到会话中，
        //在从定向后，存在会话中的flash属性会被取出，并从会话转移到模型之中
        model.addFlashAttribute("spitter", spitter);
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(Model model,
                                     @PathVariable String username) {
        if (!model.containsAttribute("spitter")) {
            Spitter spitter = spitterRepository.findByUsername(username);
            model.addAttribute(spitter);
        }
        return "profile";
    }
}
