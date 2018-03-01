package com.lzkj.lxzb.rest.modular.shiro.factory;

import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Cmsuser;
import com.lzkj.lxzb.rest.modular.shiro.ShiroUser;
import org.apache.shiro.authc.SimpleAuthenticationInfo;


/**
 * 定义shirorealm所需数据的接口
 *
 * @author fengshuonan
 * @date 2016年12月5日 上午10:23:34
 */
public interface IShiro {

    /**
     * 根据账号获取登录用户
     *
     * @param account 账号
     */
    Cmsuser user(String account);

    /**
     * 根据系统用户获取Shiro的用户
     *
     * @param cmsuser 系统用户
     */
    ShiroUser shiroUser(Cmsuser cmsuser);

    /**
     * 获取shiro的认证信息
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, Cmsuser cmsuser, String realmName);

}
