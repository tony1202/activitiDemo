package com.lw.activitidemo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Workflow {

    @RequestMapping("/workflowAction_viewHisComment")
    public String viewHisComment(long id){
        return null;
    }

    @RequestMapping("/workflowAction_startProcess")
    public String startProcess(long id){
        return null;
    }

    @RequestMapping("/workflowAction_deployHome")
    public String deployHome(){
        return null;
    }

    @RequestMapping("/workflowAction_listTask")
    public String listTask(){
        return null;
    }
}
