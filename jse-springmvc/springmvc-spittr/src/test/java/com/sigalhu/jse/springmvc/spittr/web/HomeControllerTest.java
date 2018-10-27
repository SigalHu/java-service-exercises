package com.sigalhu.jse.springmvc.spittr.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

/**
 * @author huxujun
 * @date 2018/8/17
 */
public class HomeControllerTest {

    @Test
    public void home() throws Exception {
        HomeController controller = new HomeController();
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.view().name("home"));

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                //在MockMvc构造器上调用setSingleView()，这样mock框架就不用解析控制器中的视图名了
                //对于该控制器方法，由于无法区分视图路径和控制器路径，因此按照默认视图解析规则时，MockMvc会失败
                .setSingleView(new InternalResourceView("/WEB-INF/views/home.jsp"))
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }
}