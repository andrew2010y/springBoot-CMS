package com.lzkj.lxzb.rest.modular.user.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.rest.modular.user.entity.UserExt;

import java.util.List;

/**
 * Created by panlei on 2018/2/27.
 */
public interface UserService {
    public List<UserExt> queryUserList(Page<UserExt> page,String sdkUserName);
}
