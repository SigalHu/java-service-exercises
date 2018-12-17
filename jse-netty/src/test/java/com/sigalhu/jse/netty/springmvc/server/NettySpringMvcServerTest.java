package com.sigalhu.jse.netty.springmvc.server;

import com.sigalhu.jse.netty.springmvc.config.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/12/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class NettySpringMvcServerTest {

    @Autowired
    private NettySpringMvcServer nettySpringMvcServer;

    @Test
    public void syncStart() {
        nettySpringMvcServer.syncStart();
    }
}