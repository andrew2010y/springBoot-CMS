package com.lzkj.lxzb.rest.modular.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.ChargeRecord;
import com.lzkj.lxzb.rest.modular.user.dao.ChargeRecordMappex;
import com.lzkj.lxzb.rest.modular.user.entity.ChargeRecordExt;
import com.lzkj.lxzb.rest.modular.user.service.ChargeRecordService;

/**
 * @Author：
 * @Descriptor：
 * @Date： Create in 11:45 2018/1/26
 * @Modified by：
 */
@Service
public class ChargeRecordServiceImpl implements ChargeRecordService{

    @Autowired
    private ChargeRecordMappex chargeRecordMappex;

	@Override
	public List<ChargeRecordExt> list(Page<ChargeRecordExt> page,ChargeRecordExt chargeRecordX) {
		return chargeRecordMappex.list(page,chargeRecordX);
	}

    
}
