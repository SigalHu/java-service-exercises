package com.sigalhu.jse.spring.knights;

import java.io.PrintStream;

/**
 * @author huxujun
 * @date 2018/8/6
 */
public class Minstrel {
    private PrintStream stream;

    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    public void singBeforeQuest() {
        stream.println("Fa la la, the knight is so brave!");
    }

    public void singAfterQuest() {
        stream.println("Tee hee hee, the brave knight did embark on quest!");
    }
}
