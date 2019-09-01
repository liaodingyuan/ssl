package com.liaody.ssl.base.designpattern.singleton.demo2;

public class TestMain {

    public static void main(String[] args) {

        for(int index=0;index<10;index++){
            Emperor emperor = Emperor.getInstance();
            emperor.doSomething();
        }
    }
}
