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
    private CodePerson codePerson2;

    @Autowired
    private ScanPerson scanPerson;

    @Autowired(required = false)
    private Family family1;

    @Autowired(required = false)
    private Family family2;

    @Test
    public void test() {
        Assert.assertNotNull(codePerson2);
        System.out.println(codePerson2);

        Assert.assertNotNull(scanPerson);
        System.out.println(scanPerson);

        System.out.println(family1);
        System.out.println(family2);
    }
}