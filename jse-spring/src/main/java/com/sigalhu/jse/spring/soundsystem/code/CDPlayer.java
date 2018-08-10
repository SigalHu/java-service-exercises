package com.sigalhu.jse.spring.soundsystem.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huxujun
 * @date 2018/8/7
 */
public class CDPlayer implements MediaPlayer {

    private CompactDisc cd;

    //该注解可以用在类的任何方法上，当Spring创建bean时，会传入一个CompactDisc类型的bean
    //假如有且只有一个bean匹配依赖需求的话，这个bean将会被装配进来
    //如果没有匹配的bean，那么在应用上下文创建的时候，Spring会抛出一个异常
    //可以将该注解的required属性设置为false来避免异常，但处于未装配状态的bean可能会出现NullPointerException
    //如果有多个bean都能满足依赖关系的话，Spring将会抛出一个异常，表明没有明确指定要选择哪个bean进行自动装配
    @Autowired
    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    @Override
    public void play() {
        cd.play();
    }
}
