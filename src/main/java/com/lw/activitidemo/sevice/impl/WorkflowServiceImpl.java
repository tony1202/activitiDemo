package com.lw.activitidemo.sevice.impl;

import com.lw.activitidemo.dao.EmployMapper;
import com.lw.activitidemo.dao.LeaveBillMapper;
import com.lw.activitidemo.pojo.Employee;
import com.lw.activitidemo.pojo.LeaveBill;
import com.lw.activitidemo.sevice.WorkflowService;
import com.lw.activitidemo.web.form.WorkflowBean;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private LeaveBillMapper leaveBillMapper;
    @Autowired
    private EmployMapper employMapper;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;

    @Override
    public void startProcess(WorkflowBean workflowBean) {
        //根据请假单Id查询请假单
        Long id = workflowBean.getId();
        LeaveBill leaveBill = leaveBillMapper.selectByPrimaryKey(id);

        //因为，流程的key设置与请假单实体一致
        String key = leaveBill.getClass().getSimpleName();
        //设置流程变量来制定开始任务执行者
        Map<String,Object> variables = new HashMap<>();
        Employee employee = employMapper.selectByPrimaryKey(leaveBill.getUser_id());
        variables.put("inputUser",employee.getName());
        /*(1)使用流程变量设置字符串（格式：LeaveBill.id的形式），通过设置，让启动的流程（流程实例）关联业务
         *(2)使用正在执行对象表中的一个字段BUSINESS_KEY（Activiti提供的一个字段），让启动的流程（流程实例）关联业务
        */
        String objId = key+":"+id;//流程key:业务id(使用流程变量的形式)
        variables.put("objId",objId);
        //使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
        runtimeService.startProcessInstanceByKey(key,objId,variables);
        //更改请假单的状态
        leaveBill.setState(1);
        leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
    }

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
    public void delDeployment(WorkflowBean workflowBean) {
        repositoryService.deleteDeployment(workflowBean.getDeploymentId(),true);
    }

    @Override
    public void deploy(String deploymentName, ZipInputStream zipInputStream){
        repositoryService.createDeployment().name(deploymentName).addZipInputStream(zipInputStream).deploy();
    }

    @Override
    public List<Task> findAllTaskByName(String name) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(name).orderByTaskCreateTime().desc().list();
        return list;
    }

    @Override
    public InputStream getPrcessImage(String deploymentId, String resourceName) {
        return repositoryService.getResourceAsStream(deploymentId,resourceName);
    }

    @Override
    public LeaveBill findLeaveBillByTaskId(String taskId) {
        //查询流程变量
        String objId = (String) taskService.getVariable(taskId, "objId");
        String id_str = objId.substring(objId.lastIndexOf(":")+1);
        Long id = Long.valueOf(id_str);
        return leaveBillMapper.selectByPrimaryKey(id);
    }
}
