package com.lzkj.lxzb.rest.modular.user.dao;

import com.lzkj.lxzb.lxzbEntities.entity.raw.model.UserPosition;
import com.lzkj.lxzb.rest.modular.user.entity.UserPositionExt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPositionMappex {

    List<UserPosition> selectUserPositionALL();
}
