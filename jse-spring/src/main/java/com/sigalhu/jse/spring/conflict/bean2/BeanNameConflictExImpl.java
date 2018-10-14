package com.sigalhu.jse.spring.conflict.bean2;

import com.sigalhu.jse.spring.conflict.BeanNameConflictEx;
import org.springframework.stereotype.Service;

/**
 * @author huxujun
 * @date 2018/9/19
 */
@Service
public class BeanNameConflictExImpl implements BeanNameConflictEx {

    @Override
    public void hello() {
        System.out.println("I come from bean2");
    }
}
