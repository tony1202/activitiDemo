package com.lw.activitidemo.dao;

import com.lw.activitidemo.pojo.LeaveBill;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface LeaveBillMapper extends Mapper<LeaveBill> {
}
