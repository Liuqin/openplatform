package com.lxy.openapi.web.master.mapper;

import com.lxy.openapi.web.master.pojo.AppInfo;

import java.util.List;

public interface AppInfoMapper {
    List<AppInfo> getSimpleInfoList();

    void updateAppInfo(AppInfo info);

    List<AppInfo> getInfoList(AppInfo appInfo);

    AppInfo getInfoById(int id);

    void add(AppInfo appInfo);
}
