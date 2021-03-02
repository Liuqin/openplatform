package com.lxy.openapi.web.master.controller;


import com.github.pagehelper.PageInfo;
import com.lxy.openapi.web.master.pojo.Recharge;
import com.lxy.openapi.web.master.bean.TableData;
import com.lxy.openapi.web.master.service.RechargeService;
import com.lxy.openapi.web.master.bean.AjaxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sys/recharge")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;


    @RequestMapping( "/table")
    public TableData table(Recharge recharge, Integer page, Integer limit) {
        PageInfo<Recharge> list = rechargeService.getRechargeList(recharge, page, limit);
        return new TableData(list.getTotal(), list.getList());
    }

    @RequestMapping("/add")
    public AjaxMessage add(Recharge recharge) {

        try {
            recharge.setMoney(recharge.getMoney()*10000);
            rechargeService.addRecharge(recharge);

            return new AjaxMessage(true, "添加成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new AjaxMessage(true, "添加失败");
    }

    @RequestMapping( "/update")
    public AjaxMessage update(Recharge recharge) {
        try {
            rechargeService.updateRecharge(recharge);
            return new AjaxMessage(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxMessage(false, "修改失败");
    }

    @RequestMapping( "/info")
    public Recharge info(Integer id) {
        return rechargeService.getRechargeById(id);
    }
}
