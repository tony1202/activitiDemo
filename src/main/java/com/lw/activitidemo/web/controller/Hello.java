package com.lw.activitidemo.web.controller;

import com.lw.activitidemo.pojo.LeaveBill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class Hello {



    @RequestMapping("/test")
    public String test(Model model){
        List<LeaveBill> list = new ArrayList<>();
        for (int i=0;i<3;i++){
            LeaveBill lb = new LeaveBill();
            lb.setId(Long.valueOf(i));
            lb.setDays(i);
            lb.setContent("内荣是"+i);
            lb.setLeaveDate(new Date());
            list.add(lb);
        }
        model.addAttribute("list",list);
        return "test";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "test1";
    }
}
