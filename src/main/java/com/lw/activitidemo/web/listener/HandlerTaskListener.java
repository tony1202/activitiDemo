package com.lw.activitidemo.web.listener;

import com.lw.activitidemo.sevice.EmployeeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandlerTaskListener implements TaskListener {
    @Autowired
    private EmployeeService employeeService;
    @Override
    public void notify(DelegateTask delegateTask) {
        String inputUser = (String) delegateTask.getVariable("inputUser");
        //获取当前用户的manager
        String nextAssigine = this.getNextAssgine(inputUser);
        delegateTask.setAssignee(nextAssigine);
    }

    /**
     * 获取当前用户的manager
     * @param inputUser
     * @return
     */
    public String getNextAssgine(String inputUser){

        return employeeService.findManagerByName(inputUser);
    }
}
