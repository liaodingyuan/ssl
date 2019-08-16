package yuanhaha.zk.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yuanhaha.zk.admin.entity.MyUser;

@Configuration
public class AppConfig {

    /**
     *  可以指定返回Bean的init和destory方法
     * @return
     */
    @Bean(value="user",initMethod="init",destroyMethod = "destory")
    public MyUser initUser(){
        MyUser user = new MyUser();
        user.setId(34);
        user.setAge(34);
        user.setName("yuanhaha");
        return user;
    }
}