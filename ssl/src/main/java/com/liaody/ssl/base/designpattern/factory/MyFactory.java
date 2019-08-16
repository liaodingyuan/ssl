package com.liaody.ssl.base.designpattern.factory;

public class MyFactory {

    // 从配置文件中读取配置，判断是从DB中查询数据还是从CMDB查询数据
    public static final int TYPE_MI =1;
    public static final int TYPE_YU =2;
    public static final int TYPE_SHU_SC =3;
    public static Food getFoods(int foodType){

        // 根据不同的配置，返回不同的对象
        switch (foodType) {
            case TYPE_MI:
                return new Dami();
            case TYPE_YU:
                return new YOU();
            case TYPE_SHU_SC:
            default:
                return new ShuCai();
        }
    }

}
