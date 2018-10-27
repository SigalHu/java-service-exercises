package com.sigalhu.jse.springmvc.spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * @author huxujun
 * @date 2018/8/16
 */
public class SpittrWebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //指定配置类
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        //将DispatcherServlet映射到"/"
        return new String[]{"/", "/home", "/spitter", "/spittles"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement(
                //必须要指定在文件上传的过程中，所写入的临时文件绝对路径
                "/tmp/spittr/uploads",
                //限制上传文件的大小不超过2MB
                2 * 1024 * 1024,
                //限制整个multipart请求的大小不超过4MB
                4 * 1024 * 1024,
                //最大内存大小设置为0字节，因此不管文件的大小如何，都会写到磁盘中
                0));
        super.customizeRegistration(registration);
    }
}
