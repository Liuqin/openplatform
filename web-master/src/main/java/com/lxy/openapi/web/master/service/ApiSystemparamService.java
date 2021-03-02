package com.lxy.openapi.web.master.service;

import com.github.pagehelper.PageInfo;
import com.lxy.openapi.web.master.pojo.ApiSystemparam;


public interface ApiSystemparamService {

    void addApiSystemparam(ApiSystemparam apiSystemparam) throws Exception;

    PageInfo<ApiSystemparam> getSystemparamList(ApiSystemparam criteria, int page, int limit);

    void updateApiSystemparam(ApiSystemparam systemparam);

    void deleteSystemparam(int[] ids);

    ApiSystemparam getSystemparamById(int id);
}
