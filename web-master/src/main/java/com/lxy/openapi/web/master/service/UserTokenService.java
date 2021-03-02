package com.lxy.openapi.web.master.service;

import com.github.pagehelper.PageInfo;
import com.lxy.openapi.web.master.pojo.UserToken;

public interface UserTokenService {
    PageInfo<UserToken> getTokenList(UserToken criteria, int page, int pageSize);

    UserToken getTokenById(int id);

    void updateToken(UserToken token);

    void addToken(UserToken token);

    void deleteUserToken(int[] ids);
}
