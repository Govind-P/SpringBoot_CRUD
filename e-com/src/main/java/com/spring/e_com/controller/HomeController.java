package com.spring.e_com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @RequestMapping("/")
    public String greet(){
        return "Hello world";
    }
}
