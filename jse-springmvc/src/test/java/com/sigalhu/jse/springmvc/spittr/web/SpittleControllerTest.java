package com.sigalhu.jse.springmvc.spittr.web;

import com.sigalhu.jse.springmvc.spittr.Spittle;
import com.sigalhu.jse.springmvc.spittr.data.SpittleRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                .andExpect(MockMvcResultMatchers.model().attribute("spittleList", expectedSpittles));

        expectedSpittles = createSpittleList(50);
        Mockito.when(spittleRepository.findSpittles(238900, 50))
                .thenReturn(expectedSpittles);
        //发送GET请求，同时传入max和count参数
        mockMvc.perform(MockMvcRequestBuilders.get("/spittles?max=238900&count=50"))
                .andExpect(MockMvcResultMatchers.view().name("spittles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittleList", expectedSpittles));
    }

    private List<Spittle> createSpittleList(int count) {
        List<Spittle> spittles = new ArrayList<>();
        for (int ii = 0; ii < count; ii++) {
            spittles.add(new Spittle("Spittle " + ii, new Date()));
        }
        return spittles;
    }

    @Test
    public void spittle() throws Exception {
        Spittle expectedSpittle = new Spittle("Hello", new Date());
        SpittleRepository repository = Mockito.mock(SpittleRepository.class);
        Mockito.when(repository.findOne(12345))
                .thenReturn(expectedSpittle);

        SpittleController controller = new SpittleController(repository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/spittles/12345"))
                .andExpect(MockMvcResultMatchers.view().name("spittle"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittle"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittle", expectedSpittle));
    }
}