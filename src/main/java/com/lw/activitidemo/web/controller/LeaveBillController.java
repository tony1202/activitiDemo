package com.lw.activitidemo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LeaveBillController {

    @RequestMapping("/leaveBillAction_home")
    public String home(Model model){

        return "/leaveBill/list";
    }

    @RequestMapping("/leaveBillAction_input")
    public String input(long id){

        return null;
    }

    @RequestMapping("/leaveBillAction_save")
    public String save(){
        return null;
    }

    @RequestMapping("/leaveBillAction_delete")
    public String delete(long id){
        return null;
    }
}
