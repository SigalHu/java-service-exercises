package com.sigalhu.jse.springmvc.spittr.web;

import com.sigalhu.jse.springmvc.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String spittles(Model model) {
        //Model实际上就是一个Map，它会传递给视图，这样数据就能渲染到客户端来
        //当调用addAttribute()方法并且不指定key的时候，key会根据值的对象类型推断确定（key为spittleList）
        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        //返回视图名字
        return "spittles";
    }
}
