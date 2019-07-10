package com.liaody.ssl.base.annotation;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnnotationValidatorUtil {

    /**
     * 校验实体属性不能够为null或者空字符串
     * @param entity 实体
     * @param <T> 实体类型
     * @return 校验结果
     */
    public static <T> List<ValidateResult> notNullValidate(T entity) {
        List<ValidateResult> validateResults = new ArrayList<>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 实体是否有自定义的NotNull注解
            if (field.isAnnotationPresent(NotEmpty.class)) {
                // 设置属性可以访问
                field.setAccessible(true);
                // 从实体种获取属性
                Object value = null;
                try {
                    value = field.get(entity);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                // 实体属性不能够为null或者为空字符串
                if (value == null || (value instanceof String && StringUtils.isBlank((String) value))) {
                    NotEmpty notNull = field.getAnnotation(NotEmpty.class);
                    ValidateResult validateResult = new ValidateResult();
                    validateResult.setMessage(notNull.fieldName() + "不能为空");
                    validateResults.add(validateResult);
                }
            }
        }
        return validateResults;
    }
}
