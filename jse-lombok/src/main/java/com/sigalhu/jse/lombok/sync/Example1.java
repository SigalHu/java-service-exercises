package com.sigalhu.jse.lombok.sync;

import lombok.Synchronized;

/**
 * @author huxujun
 * @date 2018/9/4
 */
public class Example1 {

    private final Object readLock = new Object();

    @Synchronized
    public static void hello() throws Exception {
        Thread.sleep(2000);
        System.out.println("hello");
    }

    @Synchronized
    public void world() throws Exception {
        Thread.sleep(1000);
        System.out.println("world");
    }

    @Synchronized("readLock")
    public void foo() {
        System.out.println("bar");
    }

//    private static final Object $LOCK = new Object[0];
//    private final Object $lock = new Object[0];
//    private final Object readLock = new Object();
//
//    public static void hello() {
//        synchronized($LOCK) {
//            System.out.println("hello");
//        }
//    }
//
//    public void world() {
//        synchronized($lock) {
//            System.out.println("world");
//        }
//    }
//
//    public void foo() {
//        synchronized(readLock) {
//            System.out.println("bar");
//        }
//    }
}
