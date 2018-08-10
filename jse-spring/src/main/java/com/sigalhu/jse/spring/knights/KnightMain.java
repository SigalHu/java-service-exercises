package com.sigalhu.jse.spring.knights;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author huxujun
 * @date 2018/8/6
 */
public class KnightMain {
    public static void main(String[] args) {
        //创建Spring应用上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knights.xml");
        //获取ID为knight的bean
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
