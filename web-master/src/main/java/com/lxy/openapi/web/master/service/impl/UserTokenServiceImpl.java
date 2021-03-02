package com.lxy.openapi.web.master.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.openapi.web.master.mapper.UserTokenMapper;
import com.lxy.openapi.web.master.pojo.UserToken;
import com.lxy.openapi.web.master.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public PageInfo<UserToken> getTokenList(UserToken criteria, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(userTokenMapper.getTokenList(criteria));
    }

    @Override
    public UserToken getTokenById(int id) {
        return userTokenMapper.getTokenById(id);
    }

    @Override
    public void updateToken(UserToken token) {
        userTokenMapper.updateToken(token);
    }

    @Override
    public void addToken(UserToken token) {
        userTokenMapper.addToken(token);
    }

    @Override
    public void deleteUserToken(int[] ids) {
        if (ids == null) {
            return;
        }
        userTokenMapper.deleteTokens(ids);
    }
}
