package com.liaody.ssl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liaody.ssl.dao")
public class SslApplication {

    public static void main(String[] args) {
        SpringApplication.run(SslApplication.class, args);
    }

}
