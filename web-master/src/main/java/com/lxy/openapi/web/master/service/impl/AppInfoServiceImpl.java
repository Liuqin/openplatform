package com.lxy.openapi.web.master.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.openapi.web.master.mapper.AppInfoMapper;
import com.lxy.openapi.web.master.mapper.CustomerMapper;
import com.lxy.openapi.web.master.pojo.AppInfo;
import com.lxy.openapi.web.master.pojo.Customer;
import com.lxy.openapi.web.master.service.AppInfoService;
import com.lxy.openapi.web.master.feign.CacheService;
import com.lxy.openplatform.commons.constans.SystemParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppInfoServiceImpl implements AppInfoService {

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CacheService cacheService;


    @Override
    public List<AppInfo> getSimpleInfoList() {
        return appInfoMapper.getSimpleInfoList();
    }

    @Override
    public void updateAppInfo(AppInfo info) {
        Customer customer = customerMapper.getCustomerById(info.getCusId());
        info.setCorpName(customer == null ? null : customer.getNickname());
        appInfoMapper.updateAppInfo(info);

        try {
            cacheService.hMSet(SystemParams.APPKEY_REDIS_PRE + info.getAppKey(),info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageInfo<AppInfo> getInfoList(AppInfo info,Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<AppInfo> infoList = appInfoMapper.getInfoList(info);
        return new PageInfo<>(infoList);
    }

    @Override
    public AppInfo getInfoById(int id) {
        return appInfoMapper.getInfoById(id);
    }

    @Override
    public void add(AppInfo appInfo) {
        Customer customer = customerMapper.getCustomerById(appInfo.getCusId());
        appInfo.setCorpName(customer == null ? null : customer.getNickname());
        appInfoMapper.add(appInfo);
        try {
            cacheService.hMSet(SystemParams.APPKEY_REDIS_PRE + appInfo.getAppKey(),appInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAppInfos(int[] ids) {
        if (ids == null || ids.length == 0) {
            return;
        }
        for (int id : ids) {
            AppInfo appInfo = appInfoMapper.getInfoById(id);
            if (appInfo != null) {
                appInfo.setState(0);
                appInfoMapper.updateAppInfo(appInfo);
                try {
                    cacheService.deleteKey(SystemParams.APPKEY_REDIS_PRE + appInfo.getAppKey());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
