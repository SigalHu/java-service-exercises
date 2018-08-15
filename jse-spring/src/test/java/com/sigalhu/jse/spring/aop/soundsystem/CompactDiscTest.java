package com.sigalhu.jse.spring.aop.soundsystem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huxujun
 * @date 2018/8/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:aop-soundsystem.xml")
public class CompactDiscTest {

    @Autowired
    private CompactDisc cd;

    @Autowired
    private TrackCounter counter;

    @Test
    public void playTrack() {
        cd.playTrack(1);
        cd.playTrack(2);
        cd.playTrack(2);
        cd.playTrack(3);
        cd.playTrack(3);
        cd.playTrack(3);

        Assert.assertEquals(1, counter.getPlayCount(1));
        Assert.assertEquals(2, counter.getPlayCount(2));
        Assert.assertEquals(3, counter.getPlayCount(3));
        Assert.assertEquals(0, counter.getPlayCount(4));
    }
}