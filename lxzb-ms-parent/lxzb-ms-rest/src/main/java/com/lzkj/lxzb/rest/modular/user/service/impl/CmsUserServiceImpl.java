package com.lzkj.lxzb.rest.modular.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzkj.lxzb.lxzbEntities.entity.raw.mapper.CmsuserMapper;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Cmsuser;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.CmsuserExample;
import com.lzkj.lxzb.rest.modular.user.service.CmsUserService;


@Service
public class CmsUserServiceImpl implements CmsUserService {
	 @Autowired
	 private CmsuserMapper cmsUserMapper;

	@Override
	public Cmsuser selectByUserName(String vc2username) {
		CmsuserExample example = new CmsuserExample();
		
		example.createCriteria().andVc2usernameEqualTo(vc2username);
		
		List<Cmsuser> list = cmsUserMapper.selectByExample(example);
		
		if (list.size()==1) {
			return list.get(0);
		}
		
		return null;
	}

	 
}
