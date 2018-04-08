package com.lw.activitidemo.sevice.impl;

import com.lw.activitidemo.sevice.WorkflowService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.zip.ZipInputStream;

@Service
public class WorkflowServiceImpl implements WorkflowService {
    @Autowired
    private RepositoryService repositoryService;

    @Override
    public List<Deployment> findAllDeployment() {
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        return list;
    }

    @Override
    public List<ProcessDefinition> findAllProcessDefinition() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().desc().list();
        return list;
    }

    @Override
    public void delDeployment(String deploymenId) {
        repositoryService.deleteDeployment(deploymenId,true);
    }

    @Override
    public void deploy(String deploymentName, ZipInputStream zipInputStream){
        repositoryService.createDeployment().name(deploymentName).addZipInputStream(zipInputStream).deploy();
    }
}
