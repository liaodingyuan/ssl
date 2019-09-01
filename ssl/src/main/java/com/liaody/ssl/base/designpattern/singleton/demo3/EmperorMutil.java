package com.liaody.ssl.base.designpattern.singleton.demo3;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Random;

/**
 * 单例模式的扩展。定义能产生最大数量的类的实例，叫做有限的多礼模式
 *
 * 有点像线程池的代码，最大线程池的数量
 *
 */
public class EmperorMutil {

    private static int maxNumEmperor = 2;
    private static ArrayList<String> nameList = Lists.newArrayList();
    private static ArrayList<EmperorMutil> emperorList = Lists.newArrayList();
    private static int countNumEmperor = 0;
    // 初始化产生多实例
    static{
        for(int index =0;index <maxNumEmperor;index++){
            emperorList.add(new EmperorMutil("屁股双"+index));
        }
    }
    private EmperorMutil(){

    }
    private EmperorMutil(String name){
        nameList.add(name);
    }

    public static EmperorMutil getInstance(){
        Random random = new Random();
        countNumEmperor = random.nextInt(maxNumEmperor);
        return emperorList.get(countNumEmperor);
    }

    public static void doSomething(){
        System.out.println(nameList.get(countNumEmperor));
    }

}
