package com.sigalhu.jse.netty.springmvc.config;

import com.sigalhu.jse.netty.springmvc.server.NettySpringMvcServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huxujun
 * @date 2018/12/17
 */
@Configuration
public class AppConfig {

    @Bean
    public NettySpringMvcServer nettySpringMvcServer() {
        return new NettySpringMvcServer(8080, WebConfig.class);
    }
}
