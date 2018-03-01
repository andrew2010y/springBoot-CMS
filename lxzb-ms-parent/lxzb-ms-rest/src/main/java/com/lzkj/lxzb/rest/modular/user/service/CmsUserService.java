package com.lzkj.lxzb.rest.modular.user.service;

import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Cmsuser;

/**
 * @Author： 
 * @Descriptor：用户充值记录
 * @Date： Create in 11:46 2018/1/26
 * @Modified by：
 */
public interface CmsUserService {
	
	Cmsuser selectByUserName(String vc2username);
	
}
