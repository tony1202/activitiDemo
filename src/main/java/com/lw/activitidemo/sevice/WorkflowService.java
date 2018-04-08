package com.lw.activitidemo.sevice;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.zip.ZipInputStream;

public interface WorkflowService {
    //流程部署
    public List<Deployment> findAllDeployment();
    public void delDeployment(String deploymenId);

    //流程定义
    public List<ProcessDefinition> findAllProcessDefinition();

    //流程发布
    public void deploy(String deploymentName,ZipInputStream zipInputStream);

}
