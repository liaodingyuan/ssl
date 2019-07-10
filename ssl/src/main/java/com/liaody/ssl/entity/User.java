package com.liaody.ssl.entity;

import com.liaody.ssl.base.annotation.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;
    @NotEmpty(fieldName="姓名")
    private String name;
    private Integer age;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
