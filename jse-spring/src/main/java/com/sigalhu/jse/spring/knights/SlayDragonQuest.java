package com.sigalhu.jse.spring.knights;

import java.io.PrintStream;

/**
 * @author huxujun
 * @date 2018/8/6
 */
public class SlayDragonQuest implements Quest {
    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void embark() {
        stream.println("Embarking on quest to slay the dragon!");
    }
}
