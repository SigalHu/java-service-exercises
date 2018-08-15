package com.sigalhu.jse.spring.aop.soundsystem;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huxujun
 * @date 2018/8/15
 */
@Component
@Aspect
public class TrackCounter {

    private Map<Integer, Integer> trackCounts = new HashMap<>();

    //args(trackNumber)限定符表明传递给playTrack()方法的int类型参数也会传递到通知中去，
    //参数的名称trackNumber也与切点方法签名中的参数相匹配
    @Pointcut("execution(* com.sigalhu.jse.spring.aop.soundsystem.BlankDisc.playTrack(int)) && args(trackNumber)")
    public void trackPlayed(int trackNumber) {
    }

    @Before("trackPlayed(trackNumber)")
    public void countTrack(int trackNumber) {
        int currentCount = getPlayCount(trackNumber);
        trackCounts.put(trackNumber, currentCount + 1);
    }

    public int getPlayCount(int trackNumber) {
        return trackCounts.getOrDefault(trackNumber, 0);
    }
}
