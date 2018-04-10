package com.lw.activitidemo.sevice;

import com.lw.activitidemo.pojo.LeaveBill;
import com.lw.activitidemo.web.form.WorkflowBean;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

public interface WorkflowService {
    //流程部署
    public List<Deployment> findAllDeployment();
    public void delDeployment(WorkflowBean workflowBean);

    //流程定义
    public List<ProcessDefinition> findAllProcessDefinition();

    //流程发布
    public void deploy(String deploymentName,ZipInputStream zipInputStream);


    //任务管理
    public List<Task> findAllTaskByName(String name);

    //启动流程
    public void startProcess(WorkflowBean key);

    //查看流程图
    public InputStream getPrcessImage(String deploymentId,String resourceName);

    //根据任务Id查找business_key,获取业务Id,
    LeaveBill findLeaveBillByTaskId(String taskId);

    List<String> findOutcomeListByTaskId(String taskId);

    List<Comment> findCommentListByTaskId(String taskId);

    //根据指定的outcome路径完成任务
    void completeTask(WorkflowBean workflowBean);

    //根据taskId查找当前节点的位置坐标
    Map<String, Double> findCoordinate(String taskId);

    //根据taskId查找相关资源
    WorkflowBean findWorkflowBeanByTaskId(String taskId);
}
