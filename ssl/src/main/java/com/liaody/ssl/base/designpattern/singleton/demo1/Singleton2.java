package com.liaody.ssl.base.designpattern.singleton.demo1;

public class Singleton2 {
    private static Singleton2 singleton2;
    private Singleton2(){

    }
    public static Singleton2 getInstance(){
        if(singleton2==null){
            synchronized (Singleton2.class){
                singleton2 = new Singleton2();
            }
        }
        return singleton2;
    }

}
