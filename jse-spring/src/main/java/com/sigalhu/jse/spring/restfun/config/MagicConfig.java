package com.sigalhu.jse.spring.restfun.config;

import com.sigalhu.jse.spring.restfun.MagicBean;
import com.sigalhu.jse.spring.restfun.MagicExistCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author huxujun
 * @date 2018/8/9
 */
@Configuration
public class MagicConfig {

    @Bean
    //使用该注解指定某个bean属于哪一个profile，只有profile激活时，才会创建对应的bean
    //若将该注解应用在配置类上，该配置类中的bean只有在profile激活时才会创建
    //此外，没有指定profile的bean始终都会被创建
    @Profile("dev")
    //该注解给定了一个实现了Condition接口的Class，它指明了条件，如果给定条件的计算结果为true，才会创建这个bean
    @Conditional(MagicExistCondition.class)
    public MagicBean devMagicBean() {
        MagicBean bean = new MagicBean();
        bean.setName("devMagicBean");
        return bean;
    }
}
