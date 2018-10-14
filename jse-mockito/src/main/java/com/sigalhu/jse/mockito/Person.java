package com.sigalhu.jse.mockito;

import java.util.Objects;

/**
 * @author huxujun
 * @date 2018/9/20
 */
public class Person {

    private Kevin kevin;

    private Zora zora;

    public void sayAll() {
        if (Objects.nonNull(kevin)) {
            System.out.println(kevin.say());
        }
        if (Objects.nonNull(zora)) {
            System.out.println(zora.say());
        }
    }
}
