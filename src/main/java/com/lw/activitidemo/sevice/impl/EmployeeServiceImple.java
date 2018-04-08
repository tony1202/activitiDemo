package com.lw.activitidemo.sevice.impl;

import com.lw.activitidemo.dao.EmployMapper;
import com.lw.activitidemo.pojo.Employee;
import com.lw.activitidemo.sevice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class EmployeeServiceImple implements EmployeeService{

    @Autowired
    private EmployMapper employMapper;

    @Override
    public Employee findByName(String name) {
        Example example = new Example(Employee.class);
        example.createCriteria().andEqualTo("name", name);
        List<Employee> employees = employMapper.selectByExample(example);
        if (employees!=null&&employees.size()>0)
            return employees.get(0);
        return null;
    }
}
