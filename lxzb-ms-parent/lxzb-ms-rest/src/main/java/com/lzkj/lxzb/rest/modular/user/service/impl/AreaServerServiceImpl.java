package com.lzkj.lxzb.rest.modular.user.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.lxzbEntities.entity.raw.mapper.AreaserverHistoryMapper;
import com.lzkj.lxzb.lxzbEntities.entity.raw.mapper.AreaserverMapper;
import com.lzkj.lxzb.lxzbEntities.entity.raw.mapper.GameuserMapper;
import com.lzkj.lxzb.lxzbEntities.entity.raw.mapper.UserPositionMapper;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.*;
import com.lzkj.lxzb.rest.modular.user.dao.AreaserverMapperx;
import com.lzkj.lxzb.rest.modular.user.dao.UserPositionMappex;
import com.lzkj.lxzb.rest.modular.user.entity.AreaserverExt;
import com.lzkj.lxzb.rest.modular.user.entity.UserPositionExt;
import com.lzkj.lxzb.rest.modular.user.service.AreaServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @Author：
 * @Descriptor：
 * @Date： Create in 11:45 2018/1/26
 * @Modified by：
 */
@Service
public class AreaServerServiceImpl implements AreaServerService {

    @Autowired
    private AreaserverMapper areaserverMapper;
    @Autowired
    private AreaserverHistoryMapper areaserverHistoryMapper;
    @Autowired
    private AreaserverMapperx areaserverMapperx;
    @Autowired
    private GameuserMapper gameuserMapper;
    @Autowired
    private UserPositionMapper userPositionMapper;

    @Autowired
    private UserPositionMappex userPositionMappex;

    @Override
    public Areaserver getByAreaCode(String vc2areacode) {
        Areaserver areaserver = null;
        AreaserverExample example = new AreaserverExample();

        example.createCriteria().andVc2areacodeEqualTo(vc2areacode);

        List<Areaserver> list = areaserverMapper.selectByExample(example);

        if (list.size() > 0) {
            areaserver = list.get(0);
        }

        return areaserver;
    }

    @Override
    public List<UserPositionExt> selectUserPositionALL() {
        List<UserPosition> userPositions = userPositionMappex.selectUserPositionALL();
        List<UserPositionExt> list = new ArrayList<UserPositionExt>();
        for (UserPosition userPosition : userPositions) {
            UserPositionExt userPositionExt = new UserPositionExt();
            userPositionExt.setNumuserpositionid(userPosition.getNumuserpositionid());
            String vc2type = userPosition.getVc2type();
            userPositionExt.setContent(this.getArrayValueByIndex(userPosition.getVc2type(), userPosition.getNumrewardretio()));
            list.add(userPositionExt);
        }
        return list;
    }

    @Override
    public int updateByExampleSelective(Areaserver areaserver, AreaserverExample example, String vc2areacode, BigDecimal numuserpositionid) {

        Areaserver areaserverOld = getByAreaCode(vc2areacode);

        Date date = areaserver.getDatcommission();

        AreaserverHistory areaserverHistory = new AreaserverHistory();

        areaserverHistory.setVc2areaname(areaserverOld.getVc2areaname());
        areaserverHistory.setVc2areacode(areaserverOld.getVc2areacode());
        areaserverHistory.setDatcreate(date);

        BigDecimal numuserid = areaserverOld.getNumuserid();

        if (numuserid != null) {
            areaserverHistory.setNumuserid(numuserid);
            areaserverHistory.setDatcommission(areaserverOld.getDatcommission());
            areaserverHistory.setDatcancel(areaserver.getDatcommission());
        } else {
            areaserverHistory.setNumuserid(areaserver.getNumuserid());
            areaserverHistory.setDatcommission(date);
        }

        // 保存历史
        areaserverHistoryMapper.insert(areaserverHistory);

        Gameuser gameuser = new Gameuser();

        GameuserExample gameuserExample = new GameuserExample();

        gameuserExample.createCriteria().andNumuseridEqualTo(areaserverHistory.getNumuserid());

        gameuser.setNumuserpositionid(numuserpositionid);

        // 保存用户角色
        gameuserMapper.updateByExampleSelective(gameuser, gameuserExample);

        // 指定代理
        return areaserverMapper.updateByExampleSelective(areaserver, example);
    }

    @Override
    public List<AreaserverExt> list(Page<AreaserverExt> page, AreaserverExt areaserverExt) {
        return areaserverMapperx.list(page, areaserverExt);
    }


    /**
     * 根据类型获取代理内容
     * @param index
     * @param mult1
     * @return
     */
    private String getArrayValueByIndex(String index, BigDecimal mult1) {
        //获取百分数
        BigDecimal mult2 = new BigDecimal(100);
        DecimalFormat df = new DecimalFormat("0");
        BigDecimal multiply = mult1.multiply(mult2);
        String format = df.format(multiply);
        Map<String, String> map = new HashMap<String, String>();
        map.put("SMALL", "小代理商（" + format + "%的收益）");
        map.put("SPECIAL", "特殊代理商（" + format + "%的收益）");
        map.put("BIG", "大代理商（" + format + "%的收益）");
        String s = map.get(index);
        return s;
    }

}
