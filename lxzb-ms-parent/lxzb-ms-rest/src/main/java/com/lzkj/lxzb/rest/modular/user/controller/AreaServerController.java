package com.lzkj.lxzb.rest.modular.user.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lzkj.lxzb.lxzbEntities.entity.raw.model.UserPosition;
import com.lzkj.lxzb.rest.modular.user.entity.UserPositionExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Areaserver;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.AreaserverExample;
import com.lzkj.lxzb.rest.base.vo.ServletResponse;
import com.lzkj.lxzb.rest.modular.user.service.AreaServerService;
import com.lzkj.lxzb.rest.modular.user.util.ResultMessageEnum;

/**
 * 服务器信息管理
 *
 * @author xkyan
 * @date 2018年01月26日 14:32:54
 */
@RestController
@RequestMapping("/area-server")
public class AreaServerController {

	@Autowired
    private AreaServerService areaServerService;


	/**
	 * 获取所有的代理
	 * @return
	 */
	@RequestMapping(value = "/getUserPositionAll", method = { RequestMethod.POST,RequestMethod.GET })
	public ServletResponse<List<UserPositionExt>> getUserPositionAll() {
		List<UserPositionExt> userPositions = areaServerService.selectUserPositionALL();
		ServletResponse<List<UserPositionExt>> result = new ServletResponse<List<UserPositionExt>>();
		result.setData(userPositions);
		result.setCode(ResultMessageEnum.success.getCode());
		result.setMsg(ResultMessageEnum.success.name());
		return result;
	}

    /**
     * 指定服务器代理
     * @param vc2areacode
     * @param newUserId
     * @param oldUserId
     * @return
     */
    @RequestMapping(value = "/{vc2areacode}/{userId}/appoint", method = { RequestMethod.POST,RequestMethod.GET })
    public ServletResponse<Integer> appoint(@PathVariable("vc2areacode") String vc2areacode,
    		@PathVariable("userId") BigDecimal newUserId,
    		@RequestParam(value = "numuserpositionid", required = true) BigDecimal numuserpositionid) {
    		
    	ServletResponse<Integer> result = new ServletResponse<Integer>();
    	
    	Areaserver areaserver = new Areaserver();
    	
    	areaserver.setNumuserid(newUserId);
    	areaserver.setDatcommission(new Date());
    	
    	AreaserverExample example = new AreaserverExample();
    	
    	example.createCriteria().andVc2areacodeEqualTo(vc2areacode);
    	
    	int updateResult = areaServerService.updateByExampleSelective(areaserver,example,vc2areacode,numuserpositionid);

        if (updateResult==1) {
        	result.setCode(ResultMessageEnum.success.getCode());
            result.setMsg(ResultMessageEnum.success.name());
        } else {
        	result.setCode(ResultMessageEnum.fail.getCode());
            result.setMsg(ResultMessageEnum.fail.name());
        }
        
        result.setData(updateResult);
        
        return result;
    }
    
}
