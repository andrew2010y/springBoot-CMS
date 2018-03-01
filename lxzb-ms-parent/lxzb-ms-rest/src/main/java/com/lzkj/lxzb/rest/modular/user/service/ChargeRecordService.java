package com.lzkj.lxzb.rest.modular.user.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.rest.modular.user.entity.ChargeRecordExt;

/**
 * @Author： 
 * @Descriptor：用户充值记录
 * @Date： Create in 11:46 2018/1/26
 * @Modified by：
 */
public interface ChargeRecordService {

    List<ChargeRecordExt> list(Page<ChargeRecordExt> page,ChargeRecordExt chargeRecordX);
}
