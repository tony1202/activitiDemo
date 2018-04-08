package com.lw.activitidemo.web.controller;

import com.lw.activitidemo.sevice.WorkflowService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

@Controller
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @RequestMapping("/workflowAction_viewHisComment")
    public String viewHisComment(long id){
        return null;
    }

    @RequestMapping("/workflowAction_startProcess")
    public String startProcess(long id){
        return null;
    }

    @RequestMapping("/workflowAction_deployHome")
    public String deployHome(Model model){
        List<Deployment> allDeployment = workflowService.findAllDeployment();
        List<ProcessDefinition> allProcessDefinition = workflowService.findAllProcessDefinition();
        model.addAttribute("depList",allDeployment);
        model.addAttribute("pdList",allProcessDefinition);
        return "/workflow/workflow";
    }

    //删除流程部署
    @RequestMapping("/workflowAction_delDeployment")
    public String delDeployment(String deploymentId){
        if (StringUtils.isNotEmpty(deploymentId)){
            workflowService.delDeployment(deploymentId);
        }
        return "forward:workflowAction_deployHome";
    }

    //发布流程
    @PostMapping("/workflowAction_newdeploy")
    public String newdeploy(MultipartFile file) throws Exception{
        String filename = file.getOriginalFilename();
        String deploymentName = filename.substring(0,filename.length()-4);
        workflowService.deploy(deploymentName,new ZipInputStream(file.getInputStream()));
        return "forward:workflowAction_deployHome";
    }

    @RequestMapping("/workflowAction_listTask")
    public String listTask(){
        return null;
    }
}
