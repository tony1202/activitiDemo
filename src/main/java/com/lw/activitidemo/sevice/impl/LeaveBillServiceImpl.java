package com.lw.activitidemo.sevice.impl;

import com.lw.activitidemo.dao.LeaveBillMapper;
import com.lw.activitidemo.pojo.LeaveBill;
import com.lw.activitidemo.sevice.LeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveBillServiceImpl implements LeaveBillService {
    @Autowired
    private LeaveBillMapper leaveBillMapper;

    @Override
    public List<LeaveBill> findAll() {
        return leaveBillMapper.selectAll();
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
