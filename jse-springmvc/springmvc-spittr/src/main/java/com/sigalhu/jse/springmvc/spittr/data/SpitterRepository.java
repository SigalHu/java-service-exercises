package com.sigalhu.jse.springmvc.spittr.data;

import com.sigalhu.jse.springmvc.spittr.Spitter;

/**
 * @author huxujun
 * @date 2018/8/18
 */
public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);
}
