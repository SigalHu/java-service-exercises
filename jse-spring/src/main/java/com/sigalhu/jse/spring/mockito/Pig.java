package com.sigalhu.jse.spring.mockito;

import org.springframework.stereotype.Service;

/**
 * @author huxujun
 * @date 2018/10/14
 */
@Service
public class Pig implements Pet {
    @Override
    public String bark() {
        return "Pig bark: grunt grunt grunt!";
    }
}
