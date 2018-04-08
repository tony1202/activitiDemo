package com.lw.activitidemo.sevice;

import com.lw.activitidemo.pojo.LeaveBill;

import java.util.List;

public interface LeaveBillService {

    public List<LeaveBill> findAll();
    public void save(LeaveBill leaveBill);

}
