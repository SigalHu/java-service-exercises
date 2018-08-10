package com.sigalhu.jse.spring.soundsystem.scan;

/**
 * 用来扫描的空标记接口，避免Spring配置引用任何实际的应用程序代码
 * 防止在重构中，这些应用代码被从想要扫描的包中移除掉
 *
 * @author huxujun
 * @date 2018/8/7
 */
public interface ScanMarker {
}
