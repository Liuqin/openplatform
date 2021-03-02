package com.lxy.openapi.web.master.service;

import com.github.pagehelper.PageInfo;
import com.lxy.openapi.web.master.pojo.Recharge;


public interface RechargeService {

    void addRecharge(Recharge recharge) throws Exception;

    PageInfo<Recharge> getRechargeList(Recharge criteria, int page, int limit);

    void updateRecharge(Recharge recharge);

    void deleteRecharge(int[] ids);

    Recharge getRechargeById(int id);
}
