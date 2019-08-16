package yuanhaha.zk.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 用于实现读取application.yml中的配置
 */
@ConfigurationProperties(prefix = MyProperties.PREFIX)
public class MyProperties {

    public static final String PREFIX = "my";

    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}