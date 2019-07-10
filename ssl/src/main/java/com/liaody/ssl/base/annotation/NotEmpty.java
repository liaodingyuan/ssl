package com.liaody.ssl.base.annotation;

import java.lang.annotation.*;

/**
 *自定义属性，校验属性为非空
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface NotEmpty {
    /**
     * 属性
     * @return 属性名称
     */
    String fieldName();

}
