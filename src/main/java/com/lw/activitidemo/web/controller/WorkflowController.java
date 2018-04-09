package com.lw.activitidemo.web.controller;

import com.lw.activitidemo.pojo.Employee;
import com.lw.activitidemo.pojo.LeaveBill;
import com.lw.activitidemo.sevice.WorkflowService;
import com.lw.activitidemo.web.form.WorkflowBean;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
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
    public String startProcess(WorkflowBean workflowBean){
        workflowService.startProcess(workflowBean);
        return "forward:leaveBillAction_home";
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
    public String delDeployment(WorkflowBean workflowBean){
        if (workflowBean!=null){
            workflowService.delDeployment(workflowBean);
        }
        return "forward:workflowAction_deployHome";
    }

    //发布流程
    @PostMapping("/workflowAction_newdeploy")
    public String newdeploy(MultipartFile file, HttpServletRequest request) throws Exception{
        String deploymentName = request.getParameter("filename");
        workflowService.deploy(deploymentName,new ZipInputStream(file.getInputStream()));
        return "forward:workflowAction_deployHome";
    }

    @RequestMapping("/workflowAction_listTask")
    public String listTask(HttpSession session,Model model){
        Employee user = (Employee) session.getAttribute("user");
        List<Task> allTaskByName = workflowService.findAllTaskByName(user.getName());
        model.addAttribute("list",allTaskByName);
        return "/workflow/task";
    }

    /**
     * 查看流程图
     * @param workflowBean
     * @return
     */
    @RequestMapping("/workflowAction_viewImage")
    public void viewImage(WorkflowBean workflowBean, HttpServletResponse response) throws IOException {
        InputStream inputStream = workflowService.getPrcessImage(workflowBean.getDeploymentId(), workflowBean.getImageName());
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            FileCopyUtils.copy(inputStream,outputStream);
        } finally {
            inputStream.close();
            outputStream.close();
        }
    }

    /**
     * 办理任务
     * @return
     */
    @RequestMapping("/workflowAction_viewTaskForm")
    public String viewTaskForm(WorkflowBean workflowBean,Model model){

        //根据任务ID查询对应业务信息
        workflowService.findLeaveBillByTaskId(workflowBean.getTaskId());
        //根据任务id查询连线的集合

        return "/workflow/taskForm";
    }
}
