package com.sigalhu.jse.springmvc.spittr.web;

import com.sigalhu.jse.springmvc.spittr.Spitter;
import com.sigalhu.jse.springmvc.spittr.data.SpitterRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author huxujun
 * @date 2018/8/17
 */
public class SpitterControllerTest {

    @Test
    public void showRegistrationForm() throws Exception {
        SpitterRepository repository = Mockito.mock(SpitterRepository.class);
        SpitterController controller = new SpitterController(repository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/spitter/register"))
                .andExpect(MockMvcResultMatchers.view().name("registerForm"));
    }

    @Test
    public void shouldProcessRegistration() throws Exception {
        SpitterRepository repository = Mockito.mock(SpitterRepository.class);
        Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
        Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
        Mockito.when(repository.save(unsaved)).thenReturn(saved);

        SpitterController controller = new SpitterController(repository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/spitter/register")
                .param("firstName", "Jack")
                .param("lastName", "Bauer")
                .param("username", "jbauer")
                .param("password", "24hours")
                .param("email", "jbauer@ctu.gov"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/spitter/jbauer"));

        Mockito.verify(repository, Mockito.atLeastOnce()).save(unsaved);
    }
}