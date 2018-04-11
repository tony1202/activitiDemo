package com.lw.activitidemo.sevice;

import com.lw.activitidemo.pojo.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee findByName(String name);

    public String findManagerByName(String name);
    List<String> findAllUser();
}
