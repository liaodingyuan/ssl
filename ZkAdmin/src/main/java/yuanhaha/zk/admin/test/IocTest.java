package yuanhaha.zk.admin.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import yuanhaha.zk.admin.config.AppConfig;
import yuanhaha.zk.admin.entity.MyUser;

@Slf4j
public class IocTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MyUser user = ctx.getBean(MyUser.class);
        log.info("user:"+user);



    }

}
