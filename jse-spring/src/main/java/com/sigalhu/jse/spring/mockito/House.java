package com.sigalhu.jse.spring.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huxujun
 * @date 2018/10/14
 */
@Service
public class House {

    @Autowired
    private Pet dog;

    @Autowired
    private Pet cat;

    public void petBark() {
        System.out.println(dog.bark());
        System.out.println(cat.bark());
    }
}
