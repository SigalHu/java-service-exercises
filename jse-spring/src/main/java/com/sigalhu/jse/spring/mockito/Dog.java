package com.sigalhu.jse.spring.mockito;

import org.springframework.stereotype.Service;

/**
 * @author huxujun
 * @date 2018/10/14
 */
@Service
public class Dog implements Pet {
    @Override
    public String bark() {
        return "Dog bark: wow wow wow!";
    }
}
