package com.lzkj.lxzb.rest.modular.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.rest.base.vo.ServletResponse;
import com.lzkj.lxzb.rest.modular.user.entity.AreaserverExt;
import com.lzkj.lxzb.rest.modular.user.service.AreaServerService;
import com.lzkj.lxzb.rest.modular.user.util.ResultMessageEnum;

@RestController
@RequestMapping("/game-suit")
public class GameSuitController {

	private Logger logger = LoggerFactory.getLogger(GameSuitController.class);

	@Autowired
    private AreaServerService areaServerService;
	
    @RequestMapping(value = "/list", method = { RequestMethod.POST,RequestMethod.GET })
    public ServletResponse<List<AreaserverExt>> list(AreaserverExt areaserverExt,
    		@RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
    		@RequestParam(value = "limit", defaultValue = "20", required = false) Integer pageSize) {
        
    	ServletResponse<List<AreaserverExt>> result = new ServletResponse<List<AreaserverExt>>();

    	Page<AreaserverExt> page = new Page<AreaserverExt>(pageNo, pageSize);
        
        List<AreaserverExt> records = areaServerService.list(page,areaserverExt);
        
        result.isSuccess(page.getTotal(),records);
        result.setCode(ResultMessageEnum.success.getCode());
        result.setMsg(ResultMessageEnum.success.name());
        
        return result;
    }
    
}
