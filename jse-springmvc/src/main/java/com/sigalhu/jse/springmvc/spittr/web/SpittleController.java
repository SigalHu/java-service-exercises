package com.sigalhu.jse.springmvc.spittr.web;

import com.sigalhu.jse.springmvc.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author huxujun
 * @date 2018/8/17
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model,
                           @RequestParam(
                                   value = "max",
                                   //defaultValue给定的是String类型的值，但是当绑定到方法的max参数时，会转换为long类型
                                   defaultValue = Long.MAX_VALUE + ""
                           ) long max,
                           @RequestParam(
                                   value = "count",
                                   defaultValue = "20"
                           ) int count) {
        //Model实际上就是一个Map，它会传递给视图，这样数据就能渲染到客户端来
        //当调用addAttribute()方法并且不指定key的时候，key会根据值的对象类型推断确定（key为spittleList）
        model.addAttribute(spittleRepository.findSpittles(max, count));
        //返回视图名字
        return "spittles";
    }

    //为了实现路径变量，可以在路径中添加占位符，路径中的其他部分要与所处理的请求完全匹配，但是占位符部分可以是任意值
    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(Model model,
                          //该注解表明在请求路径中，不管占位符部分的值是什么都会传递到处理器方法的参数中
                          //因为方法的参数名与占位符名称相同，因此可以去掉@PathVariable中的value属性
                          @PathVariable("spittleId") long spittleId) {
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }
}
