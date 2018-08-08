package com.sigalhu.jse.spring.soundsystem.xml;

import java.util.List;
import java.util.Set;

/**
 * @author huxujun
 * @date 2018/8/8
 */
public class Discography {

    private String artist;

    private Set<CompactDisc> cds;

    public Discography(String artist, Set<CompactDisc> cds) {
        this.artist = artist;
        this.cds = cds;
    }

    public void play() {
        System.out.println("Playing cds by " + artist);
        for (CompactDisc cd : cds) {
            cd.play();
        }
    }
}
