package com.lzkj.lxzb.rest.modular.user.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Areaserver;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.AreaserverExample;
import com.lzkj.lxzb.rest.modular.user.entity.AreaserverExt;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.UserPosition;
import com.lzkj.lxzb.rest.modular.user.entity.UserPositionExt;

import java.util.List;

/**
 * @Author： 
 * @Descriptor：用户充值记录
 * @Date： Create in 11:46 2018/1/26
 * @Modified by：
 */
public interface AreaServerService {

    int updateByExampleSelective(Areaserver areaserver,AreaserverExample example,String vc2areacode, BigDecimal numuserpositionid);

    Areaserver getByAreaCode(String vc2areacode);

    List<AreaserverExt> list(Page<AreaserverExt> page,AreaserverExt areaserverExt);

    List<UserPositionExt> selectUserPositionALL();

}
