package com.liaody.ssl.base.designpattern.singleton.demo2;

/**
 * 单例模式，只有一个皇帝
 */
public class Emperor {
    /**
     * 使用懒汉式式最安全的，如果该类初始化不会太久的话。
     */
    private static final Emperor EMPEROR = new Emperor();

    /**
     * 定义一个私有的构造方法，禁止外界new
     */
    private Emperor(){

    }

    /**
     * 获取Emperor实例
     * @return Emperor实例
     */
    public static Emperor getInstance(){
        return EMPEROR;
    }

    public static void doSomething(){
        System.out.println("我是屁股双");
    }
}
