package com.lzkj.lxzb.rest.modular.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.rest.modular.user.dao.UserMappex;
import com.lzkj.lxzb.rest.modular.user.entity.UserExt;
import com.lzkj.lxzb.rest.modular.user.service.UserService;

/**
 * Created by panlei on 2018/2/27.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMappex userMappex;

    @Override
    public List<UserExt> queryUserList(Page<UserExt> page,String sdkUserName) {
        return userMappex.list(page,sdkUserName);
    }
}
