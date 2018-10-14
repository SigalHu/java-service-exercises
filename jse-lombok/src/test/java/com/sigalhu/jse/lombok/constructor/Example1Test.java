package com.sigalhu.jse.lombok.constructor;

import org.junit.Test;

/**
 * @author huxujun
 * @date 2018/8/24
 */
public class Example1Test {

    @Test
    public void test() {
        try {
            Example1 ex1 = Example1.of(1, null);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        try {
            Example1 ex1 = new Example1(1, "Sigal", null, "male");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}