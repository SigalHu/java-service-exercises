package com.sigalhu.jse.spring.soundsystem.scan.config;

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
}
