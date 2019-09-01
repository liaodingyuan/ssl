package com.liaody.ssl.base.designpattern.singleton.demo1;

/**
 * 业务逻辑和单例逻辑放在一起，单例模式违背了单一职责模式
 */
public class ErrSingleton {

    /**
     * 因为资源过大，不在系统启动的时候进行加载。使用懒汉式
     */
    private static ErrSingleton errSingletonq = null;

    /**
     * 这是单例逻辑。线程不安全
     * @return 单例
     */
    public static ErrSingleton getInstance(){
        if(errSingletonq==null){
            // 线程1当进行到这一步，对象的实例还没有new出来。线程2也执行到了这里，就会new出来两个
            // 对象的实例
            // 解决方法：可以在getSingleton方法前加synchronized关键字， 也可以在getSingleton方法内增加synchronized来实现
            // 但是最好的还是应该使用饿汉式创建单例。
            errSingletonq = new ErrSingleton();
            return  errSingletonq;
        }

        return  errSingletonq;
    }

    /**
     * 使用大范围加锁，实现线程安全的单例
     * @return
     */
    public synchronized  static ErrSingleton getInstance2(){
        if(errSingletonq==null){
            errSingletonq = new ErrSingleton();
            return  errSingletonq;
        }

        return  errSingletonq;
    }

    /**
     * 使用大范围加锁，实现线程安全的单例
     * @return
     */
    public synchronized  static ErrSingleton getInstance3(){
        if(errSingletonq==null){
            // 再次进行加锁
            synchronized (Singleton2.class){
                errSingletonq = new ErrSingleton();
            }
            return  errSingletonq;
        }

        return  errSingletonq;
    }

    /**
     * 这是业务逻辑
     */
    public static void doSomething(){
        System.out.println("singleton instance dosomething！");
    }

}
