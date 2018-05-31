package com.lw.activitidemo.web.listener;

import com.lw.activitidemo.pojo.Employee;
import com.lw.activitidemo.sevice.EmployeeService;
import com.lw.activitidemo.sevice.impl.EmployeeServiceImple;
import com.lw.activitidemo.util.ApplicationContextUtil;
import com.lw.activitidemo.util.SessionUtils;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class HandlerTaskListener implements TaskListener {
    @Autowired
    private EmployeeService employeeService;
    @Override
    public void notify(DelegateTask delegateTask) {

        HttpSession session = SessionUtils.getSession();
        Employee user = (Employee) session.getAttribute("user");
        //EmployeeService employeeService = (EmployeeService) session.getAttribute("employeeService");
        String inputUser = user.getName();

        EmployeeServiceImple employeeService = (EmployeeServiceImple) ApplicationContextUtil.getBean(EmployeeServiceImple.class);

        //获取当前用户的manager
        String nextAssignee = employeeService.findManagerByName(inputUser);

        delegateTask.setAssignee(nextAssignee);
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
