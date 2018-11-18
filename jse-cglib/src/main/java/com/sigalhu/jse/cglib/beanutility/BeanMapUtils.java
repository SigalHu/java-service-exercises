package com.sigalhu.jse.cglib.beanutility;

import net.sf.cglib.beans.BeanMap;

import java.util.Map;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class BeanMapUtils {

    @SuppressWarnings("unchecked")
    public static Map<String, Object> convertToMap(Object bean) {
        return BeanMap.create(bean);
    }
}
