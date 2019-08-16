package yuanhaha.zk.admin.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

/**
 * 实现BeanpostProccesspor接口对所有的Bean初始化都有效
 * @author yuanhaha
 */
//@Component
public class BeanPostProccessorInit implements BeanPostProcessor {


    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor 调用 postProcessBeforeInitialization 方法，参数 ["
                + bean.getClass().getSimpleName()+ "][" + beanName + "]");


        return bean;
    }

    @Override
    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor 调用 postProcessAfterInitialization 方法，参数 ["
                + bean.getClass().getSimpleName()+ "][" + beanName + "]");

        return bean;
    }

}
