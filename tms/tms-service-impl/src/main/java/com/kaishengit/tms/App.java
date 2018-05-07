package com.kaishengit.tms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring*.xml");
        context.start();

        System.out.println("------------服务启动成功---------------");
        System.in.read();
    }
}