package com.sigalhu.jse.spring.person;

import com.sigalhu.jse.spring.person.config.PersonConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huxujun
 * @date 2018/8/10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersonConfig.class)
public class PersonTest {

    @Autowired
    private CodePerson codePerson;

    @Autowired
    private ScanPerson scanPerson;

    @Test
    public void test() {
        Assert.assertNotNull(codePerson);
        System.out.println(codePerson);

        Assert.assertNotNull(scanPerson);
        System.out.println(scanPerson);
    }
}