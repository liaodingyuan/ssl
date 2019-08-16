package yuanhaha.zk.admin.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动装配测试
 * @author yuanhaha
 */
@Configuration
//@EnableConfigurationProperties(StudentProperties.class)

@Import(ServerAutoConfiguration.class)
public class MyTestAutoConfiguration {




}
