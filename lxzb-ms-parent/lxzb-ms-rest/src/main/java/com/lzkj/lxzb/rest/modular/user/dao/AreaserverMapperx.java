package com.lzkj.lxzb.rest.modular.user.dao;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.rest.modular.user.entity.AreaserverExt;
import org.springframework.stereotype.Repository;

/**
 * @Author：
 * @Descriptor：
 * @Date： Create in 11:50 2018/1/26
 * @Modified by：
 */
@Repository
public interface AreaserverMapperx {

    List<AreaserverExt> list(Page<AreaserverExt> page,AreaserverExt areaserverExt);

}
