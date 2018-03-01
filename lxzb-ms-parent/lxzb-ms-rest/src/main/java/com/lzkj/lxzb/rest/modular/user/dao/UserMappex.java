package com.lzkj.lxzb.rest.modular.user.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.rest.modular.user.entity.UserExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMappex {

    List<UserExt> list(Page<UserExt> page,@Param("sdkUserName") String sdkUserName);

}
