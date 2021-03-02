package com.lxy.openapi.web.master.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.openapi.web.master.mapper.RechargeMapper;
import com.lxy.openapi.web.master.pojo.Recharge;
import com.lxy.openapi.web.master.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    private RechargeMapper rechargeMapper;

    @Override
    public void addRecharge(Recharge recharge) throws Exception {
        rechargeMapper.insertRecharge(recharge);
    }

    @Override
    public PageInfo<Recharge> getRechargeList(Recharge criteria, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Recharge> rechargeList = rechargeMapper.getAllRecharges();
        return new PageInfo<>(rechargeList);
    }

    @Override
    public void updateRecharge(Recharge recharge) {
        recharge.setUpdatetime(new Date());
        rechargeMapper.updateRecharge(recharge);
    }

    @Override
    public void deleteRecharge(int[] ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            Recharge recharge = rechargeMapper.getRechargeMapById(id);
            if (recharge != null) {
                recharge.setState(0);
                rechargeMapper.updateRecharge(recharge);
            }
        }
    }

    @Override
    public Recharge getRechargeById(int id) {
        return rechargeMapper.getRechargeMapById(id);
    }
}
