package com.lw.activitidemo.dao;

import com.lw.activitidemo.pojo.Employee;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface EmployMapper extends Mapper<Employee> {
    
}
