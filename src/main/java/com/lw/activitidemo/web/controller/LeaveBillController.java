package com.lw.activitidemo.web.controller;

import com.lw.activitidemo.pojo.Employee;
import com.lw.activitidemo.pojo.LeaveBill;
import com.lw.activitidemo.sevice.LeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LeaveBillController {
    @Autowired
    private LeaveBillService leaveBillService;

    @RequestMapping("/leaveBillAction_home")
    public String home(Model model){
        try {
            List<LeaveBill> list = leaveBillService.findAll();
            model.addAttribute("list",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/leaveBill/list";
    }

    @RequestMapping("/leaveBillAction_input")
    public String input(@RequestParam(defaultValue = "0") long id){

        return "/leaveBill/input";
    }

    @RequestMapping("/leaveBillAction_save")
    public String save(LeaveBill leaveBill, HttpSession session){
        Employee user = (Employee) session.getAttribute("user");
        leaveBill.setUser(user);
        System.out.println(leaveBill.getContent());
        return "leaveBillAction_home";
    }

    @RequestMapping("/leaveBillAction_delete")
    public String delete(long id){
        return null;
    }
}
