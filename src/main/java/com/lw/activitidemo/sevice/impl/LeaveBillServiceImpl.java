package com.lw.activitidemo.sevice.impl;

import com.lw.activitidemo.dao.EmployMapper;
import com.lw.activitidemo.dao.LeaveBillMapper;
import com.lw.activitidemo.pojo.Employee;
import com.lw.activitidemo.pojo.LeaveBill;
import com.lw.activitidemo.sevice.LeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveBillServiceImpl implements LeaveBillService {
    @Autowired
    private LeaveBillMapper leaveBillMapper;
    @Autowired
    private EmployMapper employMapper;

    @Override
    public List<LeaveBill> findAll() {
        List<LeaveBill> leaveBills = leaveBillMapper.selectAll();
        if (leaveBills!=null&&leaveBills.size()>0){
            for (LeaveBill leaveBill : leaveBills) {
                Employee employee = employMapper.selectByPrimaryKey(leaveBill.getUser_id());
                leaveBill.setUser(employee);
            }
        }
        return leaveBills;
    }

    @Override
    public void save(LeaveBill leaveBill) {
        if (leaveBill.getId()==null){
            leaveBillMapper.insert(leaveBill);
        }else{
            leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
        }
    }
}
