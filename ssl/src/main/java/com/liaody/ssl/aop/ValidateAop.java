package com.liaody.ssl.aop;

import com.liaody.ssl.base.annotation.AnnotationValidatorUtil;
import com.liaody.ssl.base.annotation.ValidateResult;
import com.liaody.ssl.constants.ResultCode;
import com.liaody.ssl.constants.ResultMessage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  使用自定义注解抽出验证框架，切面拦截所有的参数
 * @author yuanhaha
 */
@Component
@Aspect
public class ValidateAop {

    /**
     * 定义切点 @annotation 表示标注了某个注解的方法
     */
    @Pointcut("execution(* com.liaody.ssl.controller.*.*(..))")
    public void validate(){

    }
    @Around("validate()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        //参数
        Object[] args = point.getArgs();
        if(args.length != 0){
            for (Object obj:args) {
                List<ValidateResult> results =AnnotationValidatorUtil.notNullValidate(obj);
                if(!results.isEmpty()){
                    return ResultCode.BAD_REQUEST_PARAMS.withData(results);
                }
            }
        }
        //验证通过，执行方法
        return point.proceed(args);
    }

}
