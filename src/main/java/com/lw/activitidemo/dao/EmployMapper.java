package com.lw.activitidemo.dao;

import com.lw.activitidemo.pojo.Employee;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface EmployMapper extends Mapper<Employee> {

    @Select("select e.name from employee e where e.id =(select t.manager_id from employee t where t.name = #{name})")
    public String findManagerByName(String name);


    @Select("select name from employee")
    List<String> findAllUser();
    
}
