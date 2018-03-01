package com.lzkj.lxzb.rest.modular.user.dao;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.rest.modular.user.entity.ChargeRecordExt;

/**
 * @Author：
 * @Descriptor：
 * @Date： Create in 11:50 2018/1/26
 * @Modified by：
 */
public interface ChargeRecordMappex {

    List<ChargeRecordExt> list(Page<ChargeRecordExt> page,ChargeRecordExt chargeRecordX);

}
