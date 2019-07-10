package com.liaody.ssl.base.annotation;

import java.lang.annotation.*;

/**
 * 方法验证注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface EnableAuth {
}
