package com.sigalhu.jse.springmvc.spittr.web;

import com.sigalhu.jse.springmvc.spittr.Spittle;
import com.sigalhu.jse.springmvc.spittr.data.SpittleRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/8/17
 */
public class SpittleControllerTest {

    @Test
    public void spittles() throws Exception {
        List<Spittle> expectedSpittles = createSpittleList(20);
        SpittleRepository spittleRepository = Mockito.mock(SpittleRepository.class);
        Mockito.when(spittleRepository.findSpittles(Long.MAX_VALUE, 20))
                .thenReturn(expectedSpittles);

        SpittleController spittleController = new SpittleController(spittleRepository);
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(spittleController)
                //在MockMvc构造器上调用setSingleView()，这样mock框架就不用解析控制器中的视图名了
                //对于该控制器方法，由于无法区分视图路径和控制器路径，因此按照默认视图解析规则时，MockMvc会失败
                .setSingleView(new InternalResourceView("WEB-INF/views/spittles.jsp"))
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/spittles"))
                .andExpect(MockMvcResultMatchers.view().name("spittles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
                .andExpect(MockMvcResultMatchers.model()
                        .attribute("spittleList", Matchers.hasItems(expectedSpittles.toArray())));
    }

    private List<Spittle> createSpittleList(int count) {
        List<Spittle> spittles = new ArrayList<>();
        for (int ii = 0; ii < count; ii++) {
            spittles.add(new Spittle("Spittle " + ii, new Date()));
        }
        return spittles;
    }
}