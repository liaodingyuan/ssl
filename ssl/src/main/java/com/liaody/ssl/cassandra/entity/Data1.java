package com.liaody.ssl.cassandra.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class Data1 {
    private String name;
    private Map<Date,String> addrss;
    private List<String>  email;
    private Set<String> phone;
}
