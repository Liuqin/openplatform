package com.lxy.openapi.web.master.mapper;

import com.lxy.openapi.web.master.pojo.Recharge;

import java.util.List;

public interface RechargeMapper {

    int insertRecharge(Recharge object);

    int updateRecharge(Recharge object);
    int updateRechargeByorderId(Recharge object);

    int update(Recharge.UpdateBuilder object);

    List<Recharge> queryRecharge(Recharge object);

    Recharge queryRechargeLimit1(Recharge object);

    Recharge getRechargeMapById(int id);
    Recharge getRechargeMapByorderId(long orderId);

    List<Recharge> getAllRecharges();
}