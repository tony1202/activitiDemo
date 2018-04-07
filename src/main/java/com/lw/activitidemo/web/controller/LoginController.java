package com.lw.activitidemo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @PostMapping("/tologin")
    public String doLogin(String name, HttpSession session){

        if (name!=null){
            session.setAttribute("user",name);
        }
        return "main";
    }
}
