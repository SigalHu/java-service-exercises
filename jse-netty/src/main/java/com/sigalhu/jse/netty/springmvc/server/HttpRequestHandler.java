package com.sigalhu.jse.netty.springmvc.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.util.CharsetUtil;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import javax.servlet.ServletContext;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author huxujun
 * @date 2018/12/16
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final DispatcherServlet servlet;
    private final ServletContext servletContext;

    public HttpRequestHandler(DispatcherServlet servlet) {
        this.servlet = servlet;
        this.servletContext = servlet.getServletConfig().getServletContext();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        System.err.println(e.getMessage());
        if (ctx.channel().isActive()) {
            sendError(ctx, INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        boolean flag = HttpMethod.POST.equals(fullHttpRequest.method())
                || HttpMethod.GET.equals(fullHttpRequest.method());

        if (flag && ctx.channel().isActive()) {
            //HTTP请求、GET/POST
            MockHttpServletRequest servletRequest = convertRequest(fullHttpRequest);
            MockHttpServletResponse servletResponse = new MockHttpServletResponse();

            this.servlet.service(servletRequest, servletResponse);

            FullHttpResponse response = convertResponse(servletResponse);
            ChannelFuture writeFuture = ctx.writeAndFlush(response);
            writeFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private FullHttpResponse convertResponse(MockHttpServletResponse servletResponse) throws Exception {
        HttpResponseStatus status = HttpResponseStatus.valueOf(servletResponse.getStatus());
        String result = servletResponse.getContentAsString();
        result = Objects.isNull(result) ? "" : result;
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(result, CharsetUtil.UTF_8));
        for (String name : servletResponse.getHeaderNames()) {
            for (Object value : servletResponse.getHeaderValues(name)) {
                response.headers().add(name, value);
            }
        }
        return response;
    }

    private MockHttpServletRequest convertRequest(FullHttpRequest request) {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest(servletContext);

        try {
            Map<String, String> params = getRequestParams(request);

            // headers
            for (String name : request.headers().names()) {
                for (String value : request.headers().getAll(name)) {
                    servletRequest.addHeader(name, value);
                }
            }
            String uri = request.uri();
            uri = new String(uri.getBytes(CharsetUtil.ISO_8859_1), CharsetUtil.UTF_8);
            uri = URLDecoder.decode(uri, CharsetUtil.UTF_8.displayName());
            UriComponents uriComponents = UriComponentsBuilder.fromUriString(uri).build();
            String path = uriComponents.getPath();
            path = URLDecoder.decode(path, CharsetUtil.UTF_8.displayName());
            servletRequest.setRequestURI(path);
            servletRequest.setServletPath(path);
            servletRequest.setMethod(request.method().name());

            if (Objects.nonNull(uriComponents.getScheme())) {
                servletRequest.setScheme(uriComponents.getScheme());
            }
            if (Objects.nonNull(uriComponents.getHost())) {
                servletRequest.setServerName(uriComponents.getHost());
            }
            if (uriComponents.getPort() != -1) {
                servletRequest.setServerPort(uriComponents.getPort());
            }

            ByteBuf content = request.content();
            content.readerIndex(0);
            byte[] data = new byte[content.readableBytes()];
            content.readBytes(data);
            servletRequest.setContent(data);

            if (Objects.nonNull(uriComponents.getQuery())) {
                String query = UriUtils.decode(uriComponents.getQuery(), CharsetUtil.UTF_8.displayName());
                servletRequest.setQueryString(query);
            }
            params.forEach((key, value) -> {
                try {
                    servletRequest.addParameter(
                            UriUtils.decode(key, CharsetUtil.UTF_8.displayName()),
                            UriUtils.decode(Objects.isNull(value) ? "" : value, CharsetUtil.UTF_8.displayName()));
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            });
            return servletRequest;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取post请求、get请求的参数保存到map中
     */
    private Map<String, String> getRequestParams(HttpRequest req) {
        if (HttpMethod.GET.equals(req.method())) {
            // 处理get请求
            QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
            return decoder.parameters().entrySet().stream()
                    .collect(Collectors.toMap(
                            Entry::getKey,
                            entry -> entry.getValue().get(0)
                    ));
        } else if (HttpMethod.POST.equals(req.method())) {
            // 处理POST请求
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(
                    new DefaultHttpDataFactory(false), req);
            return decoder.getBodyHttpDatas().stream()
                    .filter(data -> HttpDataType.Attribute.equals(data.getHttpDataType()))
                    .map(data -> (MemoryAttribute) data)
                    .collect(Collectors.toMap(
                            MemoryAttribute::getName,
                            MemoryAttribute::getValue
                    ));
        } else {
            return Collections.emptyMap();
        }
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        ByteBuf content = Unpooled.copiedBuffer(
                "Failure: " + status.toString() + "\r\n",
                CharsetUtil.UTF_8);

        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(
                HTTP_1_1,
                status,
                content
        );
        fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");

        // Close the connection as soon as the error message is sent.
        ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
    }
}
