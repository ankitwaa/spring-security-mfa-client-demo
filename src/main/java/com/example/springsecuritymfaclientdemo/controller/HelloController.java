package com.example.springsecuritymfaclientdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/secure/hello")
    public String secureHello(){
        return "this is secure resource";
    }
}
