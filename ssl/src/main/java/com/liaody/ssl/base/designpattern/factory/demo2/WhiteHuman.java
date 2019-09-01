package com.liaody.ssl.base.designpattern.factory.demo2;

import javafx.scene.input.ScrollEvent;

public class WhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("白色");
    }

    @Override
    public void talk() {
        System.out.println("白人在说话");
    }
}
