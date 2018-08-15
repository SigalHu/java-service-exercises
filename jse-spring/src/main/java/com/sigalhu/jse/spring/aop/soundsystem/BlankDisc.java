package com.sigalhu.jse.spring.aop.soundsystem;

import java.util.List;

/**
 * @author huxujun
 * @date 2018/8/8
 */
public class BlankDisc implements CompactDisc {

    private String title;

    private String artist;

    private List<String> tracks;

    public BlankDisc() {}

    public BlankDisc(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
        for (String track : tracks) {
            System.out.println("-Track: " + track);
        }
    }

    @Override
    public void playTrack(int trackNumber) {
        System.out.println("-Track" + trackNumber + ": " + tracks.get(trackNumber));
    }
}
