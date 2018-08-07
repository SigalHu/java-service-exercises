package com.sigalhu.jse.spring.soundsystem.code;

import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/8/7
 */
public class SgtPeppers implements CompactDisc {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";

    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
