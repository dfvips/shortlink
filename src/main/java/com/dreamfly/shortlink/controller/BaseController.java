package com.dreamfly.shortlink.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    @RequestMapping("/")
    public String hello(){
        return "index";
    }
    @RequestMapping("/sorry")
    public String sorry(){
        return "sorry";
    }
}