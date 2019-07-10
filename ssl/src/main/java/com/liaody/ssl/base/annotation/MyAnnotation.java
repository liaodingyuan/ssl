package com.liaody.ssl.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE_USE,ElementType.ANNOTATION_TYPE})
public @interface MyAnnotation {

    /**
     * 注解里可以定义属性
     * @return 颜色
     */
    String color() default "blue";

    /**
     * 如果一个注解中有一个名称为value的属性，且你只想设置value属性(即其他属性都采用默认值或者你只有一个value属性)，
     * 那么可以省略掉“value=”部分。
     * @return 注解的值
     */
    String value();
    /**
     * 注解数组类型的属性
     */
    int[] arrayAttr() default {1,2,3};
    /**
     * 注解定义枚举类型属性
     */
    EumTrafficLamp lamp() default EumTrafficLamp.RED;


}
