package com.sigalhu.jse.spring.restfun;

import com.sigalhu.jse.spring.restfun.config.RestFunConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huxujun
 * @date 2018/8/9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestFunConfig.class)
//使用该注解来指定运行测试时要激活的profile
@ActiveProfiles("prod")
public class MagicBeanTest {

    @Autowired(required = false)
    private MagicBean magicBean;

    @Test
    public void test() {
        Assert.assertNotNull(magicBean);
        System.out.println(System.getenv("magic"));
        System.out.println(magicBean.getName());
    }
}