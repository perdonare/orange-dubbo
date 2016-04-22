package com.lance.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by perdonare on 2016/4/22.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:app-config.xml");
        context.start();
        while (true) {
            Thread.sleep(100);
        }
    }
}
