package com.sigalhu.jse.spring.aop.concert;

import com.sigalhu.jse.spring.aop.concert.config.ConcertConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/8/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConcertConfig.class)
public class PerformanceTest {

    @Autowired
    private Performance goodPerformance;

    @Autowired
    private Performance badPerformance;

    @Test
    public void perform() throws Exception {
        goodPerformance.perform();
        System.out.println("===================");
        badPerformance.perform();
    }
}