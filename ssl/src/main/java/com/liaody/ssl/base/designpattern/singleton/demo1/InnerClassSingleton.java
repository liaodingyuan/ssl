package com.liaody.ssl.base.designpattern.singleton.demo1;

/**
 * 使用内部类实现单例模式。
 * 加载一个类时，其内部类不会同时被加载。一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。
 * 使用静态内部实现的单例是懒加载的且线程安全。
 *
 *
 * 使用单例静态内部类是又懒又单
 *
 *
 */
public class InnerClassSingleton {

     private InnerClassSingleton(){
    }

    /**
     * 对外提供方法
     * 只有当getInstance()方法第一次被调用时，才会去初始化INSTANCE,第一次调用getInstance()方法会导致虚拟机加载InnerClass类，
     * 这种方法不仅能确保线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化。
     * @return
     */
    public static InnerClassSingleton getInstance(){
        return InnerClass.INSTANCE;
    }

    /**
     * 使用私有静态内部类禅师类的实例，类加载的时候不会初始化。当第一次调用getInstance的才会初始化。
     */
    private static class InnerClass{
        // 外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，故而不占内存
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();

    }

}
