package com.liaody.ssl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

    @GetMapping("/echo")
    public String echo(){
        return "Server Start Success!";
    }
}
