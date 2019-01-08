package com.sigalhu.jse.cglib.beanutility;

import com.sigalhu.jse.cglib.targetobject.CatImpl;
import com.sigalhu.jse.cglib.targetobject.DogImpl;
import net.sf.cglib.beans.BeanCopier;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class BeanCopyUtils {
    private static BeanCopier staticCopier = BeanCopier.create(DogImpl.class, CatImpl.class, false);

    public static <T, U> void copy(U source, T target) throws Exception {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

    public static <T, U> void staticCopy(U source, T target) throws Exception {
        staticCopier.copy(source, target, null);
    }
}
