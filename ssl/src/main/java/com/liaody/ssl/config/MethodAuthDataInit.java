package com.liaody.ssl.config;

import com.google.common.collect.Lists;
import com.liaody.ssl.base.annotation.EnableAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

//@Configuration
@Slf4j
public class MethodAuthDataInit {

    public static List<String> checkMethod = Lists.newArrayList();


    public void authMethod(ApplicationContext applicationContext) throws BeansException {

        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RestController.class);
        if(beansWithAnnotation!=null){
            for(Object bean:beansWithAnnotation.values()){
               Class<?> clz =  bean.getClass();
                Method[] declaredMethods = clz.getDeclaredMethods();
                for(Method method:declaredMethods){
                    if(method.isAnnotationPresent(EnableAuth.class)){
                       log.info("方法已经开启认证");

                    }
                }
            }
        }
    }

    private String getMethodUrl(Class<?> clz,Method method){

        StringBuilder buidler = new StringBuilder();
        buidler.append(clz.getAnnotation(RequestMapping.class).value()[0]);
        if(method.isAnnotationPresent(GetMapping.class)){
            buidler.append(clz.getAnnotation(GetMapping.class).value()[0]);
        }
        if(method.isAnnotationPresent(PostMapping.class)){
            buidler.append(clz.getAnnotation(PostMapping.class).value()[0]);
        }
        return buidler.toString();
    }
}
