package com.sigalhu.jse.springmvc.spittr.web;

import com.sigalhu.jse.springmvc.spittr.Spittle;
import com.sigalhu.jse.springmvc.spittr.data.SpittleRepository;
import com.sigalhu.jse.springmvc.spittr.exception.DuplicateSpittleException;
import com.sigalhu.jse.springmvc.spittr.exception.SpittleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    //处理对/spittles/{spittleId}的GET请求
    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(Model model,
                          //该注解表明在请求路径中，不管占位符部分的值是什么都会传递到处理器方法的参数中
                          //因为方法的参数名与占位符名称相同，因此可以去掉@PathVariable中的value属性
                          @PathVariable("spittleId") long spittleId) {
        Spittle spittle = spittleRepository.findOne(spittleId);
        if (spittle == null) {
            throw new SpittleNotFoundException();
        }
        model.addAttribute(spittle);
        return "spittle";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveSpittle(Spittle spittle, Model model) {
        try {
            spittleRepository.save(new Spittle(System.currentTimeMillis(), spittle.getMessage(), new Date(),
                    spittle.getLongitude(), spittle.getLatitude()));
            return "redirect:/spittles";
        } catch (DuplicateSpittleException e) {
            return "error/duplicate";
        }
    }

    //添加该注解，当该控制器抛出指定异常时，将会委托该方法来处理
    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleNotFound() {
        return "error/duplicate";
    }
}
