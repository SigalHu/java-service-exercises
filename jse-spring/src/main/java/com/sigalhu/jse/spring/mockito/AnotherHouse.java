package com.sigalhu.jse.spring.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huxujun
 * @date 2018/10/14
 */
@Service
public class AnotherHouse {

    @Autowired
    private Pet pig;

    @Autowired
    private Pet rabbit;

    public void petBark() {
        System.out.println(pig.bark());
        System.out.println(rabbit.bark());
    }
}
