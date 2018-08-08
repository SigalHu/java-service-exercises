package com.sigalhu.jse.spring.soundsystem.xml;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huxujun
 * @date 2018/8/7
 */
//在测试开始时自动创建Spring的应用上下文
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:soundsystem.xml")
public class CDPlayerTest {

    @Autowired
    @Qualifier("cdPlayer")
    private MediaPlayer player;

    @Autowired
    @Qualifier("anotherCDPlayer")
    private MediaPlayer anotherPlayer;

    @Test
    public void play() {
        Assert.assertNotNull(player);
        Assert.assertNotNull(anotherPlayer);
        player.play();
        anotherPlayer.play();
    }
}