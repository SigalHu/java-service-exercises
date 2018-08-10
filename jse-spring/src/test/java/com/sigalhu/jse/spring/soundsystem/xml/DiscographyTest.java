package com.sigalhu.jse.spring.soundsystem.xml;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/8/8
 */
//在测试开始时自动创建Spring的应用上下文
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:soundsystem.xml")
public class DiscographyTest {

    @Autowired
    private Discography discography;

    @Test
    public void play() {
        Assert.assertNotNull(discography);
        discography.play();
    }
}