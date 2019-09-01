package com.liaody.ssl.base.designpattern.factory.demo2;

public class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黄色");
    }

    @Override
    public void talk() {
        System.out.println("黄色人在说话");
    }
}
