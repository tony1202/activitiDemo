package com.lw.activitidemo.sevice.impl;

import com.lw.activitidemo.dao.EmployMapper;
import com.lw.activitidemo.dao.LeaveBillMapper;
import com.lw.activitidemo.pojo.Employee;
import com.lw.activitidemo.pojo.LeaveBill;
import com.lw.activitidemo.sevice.WorkflowService;
import com.lw.activitidemo.web.form.WorkflowBean;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.io.InputStream;
import java.util.ArrayList;
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
    @Autowired
    private HistoryService historyService;

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

    /**
     * 已知任务ID，查询ProcessDefinitionEntiy对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中
     * @param taskId
     * @return
     */
    @Override
    public List<String> findOutcomeListByTaskId(String taskId) {
        List<String> outcomeList = new ArrayList<>();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //获取流程定义id
        String processDefinitionId = task.getProcessDefinitionId();
        //获取流程实例Id
        String processInstanceId = task.getProcessInstanceId();
        //获取流程实例对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //获取当前活动id
        String activityId = task.getTaskDefinitionKey();
        //获取BpmnModel
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        //或去Process对象,因为只有1个流程
        Process process = bpmnModel.getProcesses().get(0);
        //获取当前节点元素
        FlowElement flowElement = process.getFlowElement(activityId);
        if (flowElement instanceof UserTask){
            UserTask userTask = (UserTask) flowElement;

            //获取所有输出路径
            List<SequenceFlow> outgoingFlows = userTask.getOutgoingFlows();
            for (SequenceFlow outgoingFlow : outgoingFlows) {
                String name = outgoingFlow.getName();
                if (StringUtils.isNotEmpty(name)){
                    outcomeList.add(name);
                }else {
                    outcomeList.add("默认提交");
                }
            }
        }

        return outcomeList;
    }

    /**
     * 获取批注信息，传递的是当前任务ID，获取历史任务ID对应的批注
     * @param taskId
     * @return
     */
    @Override
    public List<Comment> findCommentListByTaskId(String taskId) {
        //获取任务对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //获取流程实例Id
        String processInstanceId = task.getProcessInstanceId();
        List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);
        return commentList;
    }

    /**
     * 根据outcome输出路径完成任务
     * @param workflowBean
     */
    @Override
    public void completeTask(WorkflowBean workflowBean) {

        //获取任务ID
        String taskId = workflowBean.getTaskId();
        //获取业务id
        Long id = workflowBean.getId();
        //获取批注
        String comment = workflowBean.getComment();
        //获取outcome名称
        String outcome = workflowBean.getOutcome();

        /**1.在完成任务前,记录批注,方便历史查询*/
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //流程定义id
        String processInstanceId = task.getProcessInstanceId();
        /**
         * 注意：添加批注的时候，由于Activiti底层代码是使用：
         * 		String userId = Authentication.getAuthenticatedUserId();
         CommentEntity comment = new CommentEntity();
         comment.setUserId(userId);
         所有需要从Session中获取当前登录人，作为该任务的办理人（审核人），对应act_hi_comment表中的User_ID的字段，不过不添加审核人，该字段为null
         所以要求，添加配置执行使用Authentication.setAuthenticatedUserId();添加当前任务的审核人
         * */

        //添加批注
        // TODO: 2018/4/10 后期需要从act_id_user表中查去,并且该表需要和employee表同步
        Authentication.setAuthenticatedUserId(task.getAssignee());//设置为任务办理人
        taskService.addComment(taskId,processInstanceId,comment);

        /**
         * 2：如果连线的名称是“默认提交”，那么就不需要设置，如果不是，就需要设置流程变量
         * 在完成任务之前，设置流程变量，按照连线的名称，去完成任务
         流程变量的名称：outcome
         流程变量的值：连线的名称
         */
        if (!"默认提交".equals(outcome)){
            //设置流程变量
            taskService.setVariable(taskId,"outcome",outcome);
        }
        /**3.完成当前任务*/
        taskService.complete(taskId);
        /**4：当任务完成之后，需要指定下一个任务的办理人（使用类）-----已经开发完成*/

        /**5. 判断流程是否已经结束*/
        /**方式一:查询是否还有执行对象
        List<Execution> list = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
        if (list==null||list.size()==0){
            //流程已经完成,修改业务状态

        }*/
        //方式二:查询当前执流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null){
            //流程完成,修改业务状态
            LeaveBill leaveBill = leaveBillMapper.selectByPrimaryKey(id);
            leaveBill.setState(2); //完成状态
            leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
        }

    }

    /**
     * 根据任务Id查询当前节点的坐标
     * @param taskId
     * @return
     */
    @Override
    public Map<String, Double> findCoordinate(String taskId) {
        Map<String,Double> map = new HashMap<>();
        //任务对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //流程实例Id
        String processInstanceId = task.getProcessInstanceId();
        //流程定义Id
        String processDefinitionId = task.getProcessDefinitionId();
        //流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //当前活动Id
        String activityId = task.getTaskDefinitionKey();
        //bpmnModel对象
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(activityId);
        map.put("X",graphicInfo.getX());
        map.put("Y",graphicInfo.getY());
        map.put("width",graphicInfo.getWidth());
        map.put("height",graphicInfo.getHeight());
        return map;
    }

    /**
     * 根据任务Id找相关信息
     * @param taskId
     * @return
     */
    @Override
    public WorkflowBean findWorkflowBeanByTaskId(String taskId) {
        //任务对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //流程定义ID
        String processDefinitionId = task.getProcessDefinitionId();
        //流程定义对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        //部署Id
        String deploymentId = processDefinition.getDeploymentId();
        String diagramResourceName = processDefinition.getDiagramResourceName();
        WorkflowBean workflowBean = new WorkflowBean();
        workflowBean.setDeploymentId(deploymentId);
        workflowBean.setImageName(diagramResourceName);

        return workflowBean;
    }
}
