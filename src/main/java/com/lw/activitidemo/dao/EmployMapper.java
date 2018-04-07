package com.lw.activitidemo.dao;

import com.lw.activitidemo.pojo.Employee;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface EmployMapper extends Mapper<Employee> {
    
}
