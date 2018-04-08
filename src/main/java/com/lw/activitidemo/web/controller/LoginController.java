package com.lw.activitidemo.web.controller;

import com.lw.activitidemo.pojo.Employee;
import com.lw.activitidemo.sevice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/tologin")
    public String doLogin(String name, HttpSession session){

        if (name!=null){
            Employee employee = null;
            try {
                employee = employeeService.findByName(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.setAttribute("user",employee);
        }
        return "main";
    }

    @RequestMapping("/loginAction_top")
    public String top(){

        return "top";
    }

    @RequestMapping("/loginAction_left")
    public String left(){

        return "left";
    }

    @RequestMapping("/loginAction_welcome")
    public String welcome(){

        return "welcome";
    }

    @RequestMapping("/loginAction_logout")
    public String loginout(){
        return "login";
    }
}
