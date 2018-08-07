package com.sigalhu.jse.spring.soundsystem.scan;

import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/8/7
 */
//该注解表明该类会作为组件类，并告知Spring要为这个类创建bean，
//不过组件扫描默认是不启用的，需要显示配置命令Spring去寻找带有该注解的类，
//Spring会根据类名为bean指定一个id（将类名的第一个字母变为小写），也可以自己指定
@Component("lonelyHeartsClub")
public class SgtPeppers implements CompactDisc {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";

    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
