package com.sigalhu.jse.lombok.cleanup;

import lombok.Cleanup;

/**
 * @author huxujun
 * @date 2018/8/22
 */
public class Example1 {

    public static void example() {
        @Cleanup Resouce1 resouce1 = new Resouce1("resouce1");
        @Cleanup("dispose") Resouce2 resouce2 = new Resouce2("resouce2");
//        Resouce1 resouce1 = new Resouce1("resouce1");
//        try {
//            Resouce2 resouce2 = new Resouce2("resouce2");
//            try {
//
//            } finally {
//                if (resouce2 != null) {
//                    resouce2.dispose();
//                }
//            }
//        } finally {
//            if (resouce1 != null) {
//                resouce1.close();
//            }
//        }
    }
}

class Resouce1 {

    private String name;

    public Resouce1(String name) {
        this.name = name;
    }

    public void close() {
        System.out.println(name + " closed!");
    }
}

class Resouce2 {

    private String name;

    public Resouce2(String name) {
        this.name = name;
    }

    public void dispose() {
        System.out.println(name + " disposed!");
    }
}
