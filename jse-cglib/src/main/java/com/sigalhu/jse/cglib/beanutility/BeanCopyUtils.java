package com.sigalhu.jse.cglib.beanutility;

import net.sf.cglib.beans.BeanCopier;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class BeanCopyUtils {

    public static <T, U> T copy(U source, Class<T> targetClass) throws Exception {
        BeanCopier copier = BeanCopier.create(source.getClass(), targetClass, false);
        T target = targetClass.newInstance();
        copier.copy(source, target, null);
        return target;
    }
}
