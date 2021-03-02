package com.lxy.openapi.web.master.controller;

import com.github.pagehelper.PageInfo;
import com.lxy.openapi.web.master.pojo.ApiSystemparam;
import com.lxy.openapi.web.master.bean.TableData;
import com.lxy.openapi.web.master.service.ApiSystemparamService;
import com.lxy.openapi.web.master.bean.AjaxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 路由系统参数管理
 */
@RestController
@RequestMapping("/sys/systemparaters")
public class ApiSystemparamController {
    @Autowired
    private ApiSystemparamService apiSystemparamService;


    @RequestMapping("/add")
    public AjaxMessage add(ApiSystemparam systemparam) {
        try {

            apiSystemparamService.addApiSystemparam(systemparam);
            return new AjaxMessage(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxMessage(false, "添加失败");
    }


    @RequestMapping("/table")
    public TableData table(ApiSystemparam apiMapping, Integer page, Integer limit) {
        PageInfo<ApiSystemparam> pageInfo = apiSystemparamService.getSystemparamList(apiMapping, page, limit);
        return new TableData(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping("/update")
    public AjaxMessage update(ApiSystemparam systemparam) {
        try {

            apiSystemparamService.updateApiSystemparam(systemparam);
            return new AjaxMessage(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxMessage(false, "添加失败");
    }

    @RequestMapping("/del")
    public AjaxMessage delete(int[] ids) {
        try {
            apiSystemparamService.deleteSystemparam(ids);
            return new AjaxMessage(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxMessage(false, "删除失败");
    }

    @RequestMapping("/info")
    public ApiSystemparam info(int id) {
        return apiSystemparamService.getSystemparamById(id);
    }
}
