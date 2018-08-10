package com.sigalhu.jse.spring.soundsystem.xml;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huxujun
 * @date 2018/8/7
 */
public class CDPlayer implements MediaPlayer {

    private CompactDisc cd;

    public CDPlayer() {}

    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    public void setCd(CompactDisc cd) {
        this.cd = cd;
    }

    @Override
    public void play() {
        cd.play();
    }
}
