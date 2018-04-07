package com.lw.activitidemo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {

    @RequestMapping("/login")
    public String info(){
        return "login";
    }
}
