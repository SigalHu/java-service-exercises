package com.sigalhu.jse.springmvc.spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

/**
 * @author huxujun
 * @date 2018/10/28
 */
@Configuration
//启用Web安全性
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //启用默认的登录页
        http.formLogin()
                //在登陆成功之后，浏览器需要重定向到/spittles
                .successForwardUrl("/spittles")
                //添加自定义的登录页
//                .loginPage("/login")

                //配置退出行为
                .and().logout()
                //在退出成功之后，浏览器需要重定向到/
                .logoutSuccessUrl("/")
                //重写LogoutFilter的拦截路径，默认为/logout
                .logoutUrl("/logout")

                //启用Remember-me功能
                .and().rememberMe()
                .tokenRepository(new InMemoryTokenRepositoryImpl())
                //默认情况下，该功能是通过在cookie中存储一个token完成的，这个token最多两周内有效
                //在这里，我们指定这个token最多四周内有效
                .tokenValiditySeconds(2419200)
                //存储在cookie中的token包含用户名、密码、过期时间和一个私钥，在写入cookie前都进行了MD5哈希
                //默认情况下，私钥的名为SpringSecured，但在这里我们将其设置为spittrKey，使它专门用于Spittr应用
                .key("spittrKey")

                //启用HTTP Basic认证
                .and().httpBasic()
                //通过调用该方法指定域
                .realmName("Spittr")

                //包含路径的规则会按照给定的顺序发挥作用，因此，要将最为具体的请求路径放在前面，而最不具体的路径放在最后，
                //如果不这样做的话，那些不具体的路径配置将会覆盖更为具体的路径配置
                .and().authorizeRequests()
                //指定了对/spitter/me路径的请求需要进行认证
                .antMatchers("/spitter/me").authenticated()
                //对/spittles路径的POST请求必须要经过认证，且用户要具备给定角色的权限
                .antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER")
                //其他所有的请求都是允许的，不需要认证和任何的权限
                .anyRequest().permitAll()

                //借助requiresChannel()方法能够为各种url模式声明所要求的通道
                .and().requiresChannel()
                //只要是对/spitter/form的请求，都视为需要安全通道并自动将请求重定向到HTTPS上
                .antMatchers("/spitter/form").requiresSecure()
                //如果通过HTTPS发送了对/的请求，将会被重定向到不安全的HTTP通道上
                .antMatchers("/").requiresInsecure();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置自定义的用户服务
        auth.userDetailsService(userDetailsService);
        /*
                //通过inMemoryAuthentication()方法，我们可以启用、配置并任意填充基于内存的用户存储
                .inMemoryAuthentication()
                //调用withUser()方法为内存用户存储添加新的用户
                .withUser("sigalhu")
                //设置密码
                .password("sigalhu")
                //为给定用户授予一个或多个角色权限
                .roles("USER")
                .and()
                .withUser("admin").password("123456").roles("USER", "ADMIN");
        */
    }


}
