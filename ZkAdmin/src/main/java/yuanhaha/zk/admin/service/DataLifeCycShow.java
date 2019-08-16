package yuanhaha.zk.admin.service;

import org.springframework.beans.BeansException;

import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@Component
public class DataLifeCycShow implements BeanNameAware, BeanFactoryAware,ApplicationContextAware,
        BeanPostProcessor,InitializingBean, DisposableBean{

    @Override
    public void setBeanName(String s) {

        System.out.println("这是BeanNameAware中的setBeanName方法。-------》1");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("这是BeanFactoryAware中的setBeanFactory方法。----->2");
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 注意，需要容器实现ApplicationContext接口才会被调用
        System.out.println("这是ApplicationContextAware接口中setApplicationContext中的方法。---------->3");
    }
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
// 所有的Bean初始化都会执行
//        System.out.println("这是BeanPostProcessor的postProcessorBeforeInitialization方法----->4");
//
//        return bean;
//    }
    @PostConstruct
    public void init(){
        System.out.println("这是@PostConstruct注解的方法。自定义初始化方法，应该在BeanNameAwre和BeanFactory接口的响应方法执行之后执行。------>5");
    }
    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("这是InitializingBean方法的afterPropertiesSet方法------>6");
    }

//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    // 所有的Bean初始化都会执行
//        System.out.println("这是BeanPostProcessor的postProcessorAfterInitialization方法----->7");
//        return bean;
//    }

    /**
     *
     * Bean的生存周期
     */

    @PreDestroy
    public void destory(){
        System.out.println("这是使用注解@PreDestory自定义的自定义的destory----->8");
    }



    @Override
    public void destroy() throws Exception {

        System.out.println("这是DisposableBean中的destory方法------->9");
    }
}