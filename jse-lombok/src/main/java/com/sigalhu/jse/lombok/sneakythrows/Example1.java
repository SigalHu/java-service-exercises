package com.sigalhu.jse.lombok.sneakythrows;

import lombok.SneakyThrows;

import java.io.IOException;

/**
 * @author huxujun
 * @date 2018/9/3
 */
public class Example1 {

    @SneakyThrows
    public void throwThrowable() {
        throw new Throwable();
    }

    @SneakyThrows(IOException.class)
    public void throwIOException() {
        throw new IOException();
    }

    public void throwRuntimeException() {
        throw new RuntimeException();
    }
}
