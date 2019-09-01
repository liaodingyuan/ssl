package com.liaody.ssl.base.designpattern.singleton.demo;

import com.liaody.ssl.base.designpattern.singleton.demo1.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 属于工厂方法的扩展:使用工厂方法产生单例
 */
public class SingletonFactory {

    public static Singleton singleton= null;

    /**
     * 知识点补充：
     *
     * 类被加载了不一定就会执行静态代码块，只有一个类被主动使用的时候，静态代码才会被执行！==
     * 当一个类被主动使用时，Java虚拟就会对其初始化，如下六种情况为主动使用：
     * 当创建某个类的新实例时（如通过new或者反射，克隆，反序列化等）
     * 当调用某个类的静态方法时
     * 当使用某个类或接口的静态字段时
     * 当调用Java API中的某些反射方法时，比如类Class中的方法，或者java.lang.reflect中的类的方法时
     * 当初始化某个子类时
     * 当虚拟机启动某个被标明为启动类的类（即包含main方法的那个类）
     * Java编译器会收集所有的类变量初始化语句和类型的静态初始化器，将这些放到一个特殊的方法中：clinit。
     *
     * 使用final修饰的静态字段，在调用的时候不会对类进行初始化！
     */
    static {
        try {
            Class cl = Class.forName(Singleton.class.getName());
            // 获取构造方法
            Constructor constructors = cl.getDeclaredConstructor();
            // 私有构造方法设置为可访问
            constructors.setAccessible(true);
            singleton = (Singleton)constructors.newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static  Singleton getIstance(){
        return singleton;
    }



}
