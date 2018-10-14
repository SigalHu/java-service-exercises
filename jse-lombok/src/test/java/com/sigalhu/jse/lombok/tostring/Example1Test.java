package com.sigalhu.jse.lombok.tostring;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/8/22
 */
public class Example1Test {

    @Test
    public void test() {
        int id = 1;
        String name = "sigal";
        int width = 5;
        int height = 10;
        String[] tags = new String[]{"a", "b"};

        Example1 ex1 = new Example1(id, name, width, height, tags);
        System.out.println(ex1.toString());
    }
}