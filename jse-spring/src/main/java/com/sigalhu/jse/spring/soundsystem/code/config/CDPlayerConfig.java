package com.sigalhu.jse.spring.soundsystem.code.config;

import com.sigalhu.jse.spring.soundsystem.code.CDPlayer;
import com.sigalhu.jse.spring.soundsystem.code.CompactDisc;
import com.sigalhu.jse.spring.soundsystem.code.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author huxujun
 * @date 2018/8/7
 */
//该注解表明该类是一个配置类
@Configuration
//该注解能够在Spring中启用组件扫描，默认会扫描与配置类相同的包，
//也可以在该注解中指明包的名称，若想设置多个包，则使用数组形式，即{"package1", "package2"}
@ComponentScan("com.sigalhu.jse.spring.soundsystem")
//另一种方法，basePackageClasses属性所设置的数组中包含的类所在的包将会作为组件扫描的基础包
//@ComponentScan(basePackageClasses = {ScanMarker.class})
public class CDPlayerConfig {

    //该注解会告诉Spring该方法将会返回一个对象，该对象要注册为Spring应用上下文中的bean
    //默认情况下，bean的id与该方法名一样，也可以通过name属性指定一个不同的名字
    @Bean(name = "lonelyHeartsClueBand")
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }

    @Bean
    public CDPlayer cdPlayer() {
        //由于sgtPeppers()方法上添加了@Bean注解，Spring将会拦截所有对它的调用，
        //并确保直接返回该方法所创建的bean，而不是每次都对其进行实际的调用，
        //默认情况下，Spring中的bean都是单例的
        return new CDPlayer(sgtPeppers());
    }

    //该方式会自动装配一个CompactDisc到配置方法之中，在这里没有要求CompactDisc必须要在JavaConfig中声明，
    //实际上它可以通过组件扫描功能自动发现或通过xml来进行配置，只要功能完整健全即可
    @Bean
    public CDPlayer anotherCDPlayer(CompactDisc cd) {
        return new CDPlayer(cd);
    }
}
