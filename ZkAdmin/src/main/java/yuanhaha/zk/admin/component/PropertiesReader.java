package yuanhaha.zk.admin.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 测试从不同的配置文件中读取数据
 */
@Component
@ConfigurationProperties("datasource")
public class PropertiesReader {

    private String name;
    private String type;
    private String url;
    private String username;
    private String password;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        System.out.println("name=======================================================================:"+name);
        this.name = name;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        System.out.println("type:"+type);
        this.type = type;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        System.out.println("url:"+url);
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("username:"+username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println("password:"+password);
        this.password = password;
    }
}
