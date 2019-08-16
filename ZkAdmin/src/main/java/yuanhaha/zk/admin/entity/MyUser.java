package yuanhaha.zk.admin.entity;

import lombok.Data;

@Data
public class MyUser {

    private Integer id;
    private Integer age;
    private String name;
    public void init(){

        System.out.println("user 的自定init方法");
    }
    public void destory(){

        System.out.println("user 的自定destory方法");
    }

}
