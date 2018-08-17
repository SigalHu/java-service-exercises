package com.sigalhu.jse.springmvc.spittr.data;

import com.sigalhu.jse.springmvc.spittr.Spittle;

import java.util.List;

/**
 * @author huxujun
 * @date 2018/8/17
 */
public interface SpittleRepository {

    /**
     * 获取Spittle列表
     *
     * @param max   所返回的Spittle中，Spittle id属性的最大值
     * @param count 要返回多少个Spittle对象
     * @return
     */
    List<Spittle> findSpittles(long max, int count);
}
