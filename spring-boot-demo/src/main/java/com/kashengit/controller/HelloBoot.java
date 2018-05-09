package com.kashengit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloBoot {
    @GetMapping("/hello")
    public String Hello(){
        return "你好啊！springBoot";
    }

}
