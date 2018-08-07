package com.sigalhu.jse.spring.soundsystem.scan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author huxujun
 * @date 2018/8/7
 */
//在测试开始时自动创建Spring的应用上下文
@RunWith(SpringJUnit4ClassRunner.class)
//告诉Spring在CDPlayerConfig中加载配置
//@ContextConfiguration(classes = CDPlayerConfig.class)
@ContextConfiguration(locations = "classpath*:soundsystem.xml")
public class SgtPeppersTest {

    @Autowired
    private CompactDisc cd;

    @Test
    public void play() {
        assertNotNull(cd);
        cd.play();
    }
}