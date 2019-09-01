package com.liaody.ssl.base.designpattern.singleton.demo1;

/**
 * 单例模式
 * @author yuanhaha
 */
public class Singleton {
    // 懒汉模式，线程安全
    private static Singleton singleton = new Singleton();
    private Singleton(){

    }
    public static Singleton getInstance(){
        return singleton;
    }


}
