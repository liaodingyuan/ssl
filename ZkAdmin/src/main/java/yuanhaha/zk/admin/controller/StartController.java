package yuanhaha.zk.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yuanhaha.zk.admin.component.PropertiesReader;

/**
 * @author yuanhaha
 */
@RestController
public class StartController {

    @Autowired
    PropertiesReader propertiesReader;

    @GetMapping("/zkadmin/echo")
    public Object echo() {
        System.out.println(propertiesReader.getName());
        System.out.println(propertiesReader.getPassword());
        System.out.println(propertiesReader.getType());
        System.out.println(propertiesReader.getUrl());
        System.out.println(propertiesReader.getUsername());
        return "ZKAdmin Start Success!";
    }
}
