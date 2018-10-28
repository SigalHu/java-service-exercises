package com.sigalhu.jse.springmvc.spittr.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * AbstractSecurityWebApplicationInitializer实现了WebApplicationInitializer，因此Spring会发现它，
 * 并用它在Web容器中注册DelegatingFilterProxy，它会拦截发往应用中的请求，并将其委托给ID为springSecurityFilterChain的bean
 *
 * @author huxujun
 * @date 2018/10/28
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
}
