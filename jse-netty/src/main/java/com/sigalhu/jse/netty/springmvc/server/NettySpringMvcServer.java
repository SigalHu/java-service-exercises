package com.sigalhu.jse.netty.springmvc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.util.Assert;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author huxujun
 * @date 2018/12/14
 */
public class NettySpringMvcServer implements ApplicationContextAware {
    private static final String DEFAULT_DISPATCHER_SERVLET = "dispatcherServlet";

    private Integer port;
    private Class webConfig;
    private Thread serverThread;
    private ApplicationContext applicationContext;
    private DispatcherServlet dispatcherServlet;

    public NettySpringMvcServer(Integer port, Class webConfig) {
        checkInitParam(port, webConfig);
        this.port = port;
        this.webConfig = webConfig;
    }

    private void checkInitParam(Integer port, Class webConfig) {
        Assert.notNull(port, "The port must not be null!");
        Assert.notNull(webConfig, "The webConfig must not be null!");
        Assert.isTrue(webConfig.isAnnotationPresent(EnableWebMvc.class), "The webConfig must be annotated with EnableWebMvc!");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void asyncStart() {
        this.serverThread = new Thread(this::syncStart);
        this.serverThread.start();
    }

    public void syncStart() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            dispatcherServlet = createDispatcherServlet(webConfig, applicationContext);

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", new HttpRequestDecoder());
                            pipeline.addLast("encoder", new HttpResponseEncoder());
                            pipeline.addLast("aggregator", new HttpObjectAggregator(2147483647));
                            pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
                            pipeline.addLast("deflater", new HttpContentCompressor());
                            pipeline.addLast("handler", new HttpRequestHandler(dispatcherServlet));
                        }
                    });

            System.out.println("NettySpringMvcServer has started on port:" + port);
            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();
            // 等待服务器 socket 关闭 。在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private DispatcherServlet createDispatcherServlet(Class webConfig, ApplicationContext applicationContext) throws Exception {
        MockServletContext servletContext = new MockServletContext();
        MockServletConfig servletConfig = new MockServletConfig(servletContext, DEFAULT_DISPATCHER_SERVLET);

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setParent(applicationContext);
        context.setServletConfig(servletConfig);
        context.register(webConfig);
        context.refresh();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        dispatcherServlet.init(servletConfig);
        return dispatcherServlet;
    }
}
